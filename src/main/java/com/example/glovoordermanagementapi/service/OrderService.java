package com.example.glovoordermanagementapi.service;


import com.example.glovoordermanagementapi.exception.BadRequestException;
import com.example.glovoordermanagementapi.exception.NotFoundException;
import com.example.glovoordermanagementapi.model.Order;
import com.example.glovoordermanagementapi.model.Product;

import java.util.List;

public interface OrderService {
    Order createOrder(int orderNew) throws BadRequestException;
    Order updateOrder(Order order) throws NotFoundException;
    Order getOrderById(int orderId) throws NotFoundException;
    void deleteOrder(Order order) throws NotFoundException;
    void addProductToOrder(Order order, Product product) throws NotFoundException;
    void removeProductFromOrder(Order order, Product product) throws NotFoundException;
    List<Order> getAllOrders();
    void displayAllOrdersInfo();
}
