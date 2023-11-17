package com.example.test.repository;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtRepository {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaim, UserDetails userDetails);
}