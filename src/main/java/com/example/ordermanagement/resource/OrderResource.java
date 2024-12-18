package com.example.ordermanagement.resource;

import com.example.ordermanagement.model.dto.OrderDto;
import com.example.ordermanagement.model.entity.Order;
import com.example.ordermanagement.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(
        final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
        @RequestBody
        final OrderDto orderDto
    ) {
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("{orderId}")
    public ResponseEntity<Order> updateOrder(
        @PathVariable
        final Long orderId,
        @RequestBody
        final OrderDto orderDto
    ) {
        return orderService.updateOrder(orderId, orderDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
        @PathVariable
        final Long orderId
    ) {
        return orderService.getOrderById(orderId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(
        @PathVariable
        final Long orderId
    ) {
        return orderService.deleteOrder(orderId)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}
