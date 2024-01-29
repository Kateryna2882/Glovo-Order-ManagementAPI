package com.example.glovoordermanagementapi.controller;


import com.example.glovoordermanagementapi.exception.NotFoundException;
import com.example.glovoordermanagementapi.model.Order;
import com.example.glovoordermanagementapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @SneakyThrows
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam int orderId) {
        if (orderId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Order createdOrder = orderService.createOrder(orderId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder)
            throws NotFoundException {
        if (orderId <= 0 || updatedOrder == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Order updated = orderService.updateOrder(updatedOrder);
            return ResponseEntity.ok(updated);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @SneakyThrows
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @SneakyThrows
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        try {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {
                orderService.deleteOrder(existingOrder);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
