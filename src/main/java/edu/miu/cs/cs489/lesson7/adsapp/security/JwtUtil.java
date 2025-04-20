package edu.miu.cs.cs489.lesson7.adsapp.security;

import edu.miu.cs.cs489.lesson7.adsapp.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // ✅ At least 32 characters for HS256 (256 bits)
    private static final String SECRET_KEY = "72a22966eedaeb56da6146095f1bbc5b1421c011194fbc07a0f917c8819dee790ae99969dccc7a2895288f347f02bd47e2287c9cd253a2e2449828b63b833bbc1eea580ad3b504dccf7a418a681e58d646ab5a5754427abdc27f9f0aa6498d14783ddff30717a3fc0af46522d437afbd01c89b6f27ceb3c8620e5b4ea580593c2a4bb1375a618b364a1d745aa6d675ad7673c8bdb1b86de005eff42f6f88e12400daeeb1183cab904bf0f8efcdfaa16e650f57feadd6dd151625dcd6f6ec0cd46227fcc3da71a0f2cdb0180896d98758d573cabedef07656440fe06eb1c9d38a4a61ad0ec7565109e9e31e589df3c23b9e8a2689990f15db930a23b7e5cb47bf";

//    private static final String SECRET_KEY = "super-secret-key-for-jwt-signing-256";

    private static final long EXPIRATION_TIME_MS = 1000 * 60 * 60; // 1 hour
    private final UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_" + user.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
