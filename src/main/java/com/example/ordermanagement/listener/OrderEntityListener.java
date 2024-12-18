package com.example.ordermanagement.listener;

import com.example.ordermanagement.model.entity.Order;
import com.example.ordermanagement.service.EmailService;

import jakarta.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityListener {

    private static EmailService emailService;

    @Autowired
    public void setEmailService(final EmailService emailService) {
        OrderEntityListener.emailService = emailService;
    }

    @PostUpdate
    public void afterUpdate(final Order order) {
        if (order.getIsComplete()) {
            sendCompletionEmail(order);
        }
    }

    private void sendCompletionEmail(final Order order) {
        String subject = "Order Completed";
        String message = String.format("The order with ID %s has been completed.", order.getId());

        emailService.sendSimpleEmail(order.getUser().getEmail(), subject, message);
    }
}
