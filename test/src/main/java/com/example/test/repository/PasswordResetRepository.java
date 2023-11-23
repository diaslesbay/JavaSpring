package com.example.test.repository;

public interface PasswordResetRepository {
    void requestPasswordReset(String email);
    void resetPassword(String token, String newPassword);
}
