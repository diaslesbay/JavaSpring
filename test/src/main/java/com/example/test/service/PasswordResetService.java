package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.JwtRepository;
import com.example.test.repository.PasswordResetRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String resetToken = generateToken();
            user.setResetToken(resetToken);
            userRepository.save(user);
            sendResetEmail(user.getEmail(), resetToken);
        }
    }

    public boolean resetPassword(String email, String resetToken, String newPassword) {
        User user = userRepository.findUserByEmailAndResetToken(email, resetToken);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
            return true;
        }else
            return false;

    }
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private void sendResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println(email+" "+token);
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n\n"
                + "http://your-app-url/reset-password?token=" + token);
        System.out.println(message);
        javaMailSender.send(message);
    }
}
