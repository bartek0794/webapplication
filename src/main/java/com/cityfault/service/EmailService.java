package com.cityfault.service;

public interface EmailService {
    void prepareAndSend(String recipient, String department, String status, String priority, String description, String title);
}
