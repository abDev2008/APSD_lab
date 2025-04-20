package edu.miu.cs.cs489.lesson7.adsapp.service;

import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthRequest;
import edu.miu.cs.cs489.lesson7.adsapp.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
