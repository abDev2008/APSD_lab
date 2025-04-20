package edu.miu.cs.cs489.lesson7.adsapp.service.impl;

import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthRequest;
import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthResponse;
import edu.miu.cs.cs489.lesson7.adsapp.model.Role;
import edu.miu.cs.cs489.lesson7.adsapp.model.User;
import edu.miu.cs.cs489.lesson7.adsapp.repository.UserRepository;
import edu.miu.cs.cs489.lesson7.adsapp.security.JwtUtil;
import edu.miu.cs.cs489.lesson7.adsapp.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        var user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        // ✅ Force role to ADMIN for now
//        user.setRole(Role.ADMIN);
        user.setRole(request.role() != null ? request.role() : Role.USER);

        userRepository.save(user);

        var token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }

        var token = jwtUtil.generateToken(request.username());
        return new AuthResponse(token);
    }
}
