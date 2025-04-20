package edu.miu.cs.cs489.lesson7.adsapp.controller;


import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthRequest;
import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthResponse;
import edu.miu.cs.cs489.lesson7.adsapp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
