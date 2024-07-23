package com.example.services;

public interface EmailService {
    void sendConfirmationEmail(String email, String token);
}
