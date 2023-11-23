package com.example.test.controller;

import com.example.test.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordRecoveryService;

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        passwordRecoveryService.requestPasswordReset(email);
        System.out.println("forgot");
        return ResponseEntity.ok("Password reset email sent successfully");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String resetToken,
                                                @RequestParam String newPassword) {

        return ResponseEntity.ok(
                passwordRecoveryService.resetPassword(email, resetToken, newPassword)
                        ? "Password reset successfully"
                        : "Email or token is wrong!");
    }
}
