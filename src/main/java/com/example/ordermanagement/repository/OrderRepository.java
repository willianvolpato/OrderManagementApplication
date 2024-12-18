package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
