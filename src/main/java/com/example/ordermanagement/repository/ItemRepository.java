package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}
