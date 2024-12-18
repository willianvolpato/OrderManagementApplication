package com.example.ordermanagement.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDto{

    private Long id;

    private LocalDateTime creationDate;

    private ItemDto item;

    private Integer quantity;

    private UserDto user;

    private Boolean isComplete;
}
