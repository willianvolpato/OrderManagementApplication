package com.example.ordermanagement.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.util.ObjectUtils;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;

    @ManyToOne
    private Item item;

    private Integer quantity;

    public void setCreationDate(final LocalDateTime creationDate) {
        if(!ObjectUtils.isEmpty(creationDate))
            this.creationDate = creationDate;
    }

    public void setItem(final Item item) {
        if(!ObjectUtils.isEmpty(item))
            this.item = item;
    }

    public void setQuantity(final Integer quantity) {
        if(!ObjectUtils.isEmpty(quantity))
            this.quantity = quantity;
    }
}
