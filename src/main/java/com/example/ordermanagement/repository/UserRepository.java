package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
