package com.example.glovoordermanagementapi.service;


import com.example.glovoordermanagementapi.exception.BadRequestException;
import com.example.glovoordermanagementapi.exception.NotFoundException;
import com.example.glovoordermanagementapi.model.Order;
import com.example.glovoordermanagementapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public Order createOrder(int orderId) throws BadRequestException {
        if (orderId <= 0) {
            throw new BadRequestException("Invalid order ID");
        }

        Order order = new Order(orderId);
        orderMap.put(orderId, order);
        return order;
    }

    @Override
    public Order updateOrder(Order updatedOrder) throws NotFoundException {
        int orderId = updatedOrder.getOrderId();
        if (!orderMap.containsKey(orderId)) {
            throw new NotFoundException("Order not found");
        }

        Order existingOrder = orderMap.get(orderId);
        existingOrder.setProducts(updatedOrder.getProducts());

        return existingOrder;
    }

    @Override
    public Order getOrderById(int orderId) throws NotFoundException {
        if (!orderMap.containsKey(orderId)) {
            throw new NotFoundException("Order not found");
        }

        return orderMap.get(orderId);
    }

    @Override
    public void deleteOrder(Order order) throws NotFoundException {
        int orderId = order.getOrderId();
        if (!orderMap.containsKey(orderId)) {
            throw new NotFoundException("Order not found");
        }

        orderMap.remove(orderId);
    }

    @Override
    public void addProductToOrder(Order order, Product product) throws NotFoundException {
        int orderId = order.getOrderId();
        if (!orderMap.containsKey(orderId)) {
            throw new NotFoundException("Order not found");
        }

        // Implement your logic to add the product to the order
        order.addProduct(product);
    }

    @Override
    public void removeProductFromOrder(Order order, Product product) throws NotFoundException {
        int orderId = order.getOrderId();
        if (!orderMap.containsKey(orderId)) {
            throw new NotFoundException("Order not found");
        }

        // Implement your logic to remove the product from the order
        order.removeProduct(product);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public void displayAllOrdersInfo() {
        for (Order order : orderMap.values()) {
            order.displayOrderInfo();
        }
    }
}
