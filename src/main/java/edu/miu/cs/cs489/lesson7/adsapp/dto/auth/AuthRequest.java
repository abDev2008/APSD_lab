package edu.miu.cs.cs489.lesson7.adsapp.dto.auth;

import edu.miu.cs.cs489.lesson7.adsapp.model.Role;

public record AuthRequest(String username, String password, Role role) {}
