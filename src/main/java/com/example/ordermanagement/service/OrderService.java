package com.example.ordermanagement.service;

import com.example.ordermanagement.model.dto.OrderDto;
import com.example.ordermanagement.model.entity.Order;
import com.example.ordermanagement.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public OrderService(
        final OrderRepository orderRepository,
        final ItemService itemService,
        final UserService userService
    ) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
        this.userService = userService;
    }

    public Order createOrder(final OrderDto orderDto) {
        Order order = Order.builder()
            .creationDate(orderDto.getCreationDate())
            .item(itemService.getOrCreateItem(orderDto.getItem()))
            .quantity(orderDto.getQuantity())
            .user(userService.getOrCreateUser(orderDto.getUser()))
            .isComplete(orderDto.getIsComplete())
            .build();

        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(
        final Long orderId,
        final OrderDto orderDto
    ) {
        return this.getOrderById(orderId)
            .map(orderEntity -> {
                orderEntity.setCreationDate(orderDto.getCreationDate());
                orderEntity.setItem(itemService.getOrCreateItem(orderDto.getItem()));
                orderEntity.setQuantity(orderDto.getQuantity());
                orderEntity.setUser(userService.getOrCreateUser(orderDto.getUser()));
                orderEntity.setIsComplete(orderDto.getIsComplete());
                return orderRepository.saveAndFlush(orderEntity);
            });
    }

    public Optional<Order> getOrderById(final Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public boolean deleteOrder(final Long orderId) {
        Optional<Order> order = this.getOrderById(orderId);

        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return true;
        }

        return false;
    }
}
