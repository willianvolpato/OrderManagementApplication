package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.entity.StockMovement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {}
