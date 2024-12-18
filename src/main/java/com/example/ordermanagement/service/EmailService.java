package com.example.ordermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(
        final JavaMailSender mailSender
    ) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(
        final String to,
        final String subject,
        final String text
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("orderManagementApplication@mail.com"); // Optional: Specify the "from" address

        mailSender.send(message);
    }
}
