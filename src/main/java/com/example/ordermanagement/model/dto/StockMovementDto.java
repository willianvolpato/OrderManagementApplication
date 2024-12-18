package com.example.ordermanagement.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StockMovementDto{

    private LocalDateTime creationDate;

    private ItemDto item;

    private Integer quantity;
}
