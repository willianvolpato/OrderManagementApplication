package com.example.ordermanagement.model.entity;

import com.example.ordermanagement.listener.OrderEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.util.ObjectUtils;

@Entity
@EntityListeners(OrderEntityListener.class)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;

    @ManyToOne
    private Item item;

    private Integer quantity;

    @ManyToOne
    private User user;

    private Boolean isComplete;

    public void setCreationDate(final LocalDateTime creationDate) {
        if(!ObjectUtils.isEmpty(creationDate))
            this.creationDate = creationDate;
    }

    public void setItem(final Item item) {
        if(!ObjectUtils.isEmpty(item))
            this.item = item;
    }

    public void setQuantity(Integer quantity) {
        if(!ObjectUtils.isEmpty(quantity))
            this.quantity = quantity;
    }

    public void setUser(final User user) {
        if(!ObjectUtils.isEmpty(user))
            this.user = user;
    }

    public void setIsComplete(final Boolean isComplete) {
        if(!ObjectUtils.isEmpty(isComplete))
            this.isComplete = isComplete;
    }
}
