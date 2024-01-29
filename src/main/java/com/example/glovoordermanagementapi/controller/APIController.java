package com.example.glovoordermanagementapi.controller;

import com.example.glovoordermanagementapi.exception.NotFoundException;
import com.example.glovoordermanagementapi.model.Order;
import com.example.glovoordermanagementapi.model.Product;
import com.example.glovoordermanagementapi.service.OrderService;
import com.example.glovoordermanagementapi.service.ProductService;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Controller
@RequestMapping("/api/orders")
public class APIController {
    private static ProductService productService;
    private final OrderService orderService;

    @SneakyThrows
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam int orderId) {
        if (orderId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order createdOrder = orderService.createOrder(orderId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) throws NotFoundException {
        ResponseEntity<Order> result;
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            result = new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder)
            throws NotFoundException {
        Order existingOrder = orderService.getOrderById(orderId);

        if (existingOrder != null) {

            existingOrder.setProducts(updatedOrder.getProducts());


            orderService.updateOrder(existingOrder);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/displayAllInfo")
    public ResponseEntity<Void> displayAllOrdersInfo() {
        orderService.displayAllOrdersInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RestController
    @RequestMapping("/api/orders")
    public static class OrderController {

        private final OrderService orderService;

        @Autowired
        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        @DeleteMapping("/{orderId}")
        public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) throws NotFoundException {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {

                orderService.deleteOrder(existingOrder);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @SneakyThrows
        @PatchMapping("/{orderId}/addProduct")
        public ResponseEntity<Void> addProductToOrder(@PathVariable int orderId, @RequestBody Product product) {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {

                orderService.addProductToOrder(existingOrder, product);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @SneakyThrows
        @PatchMapping("/{orderId}/removeProduct")
        public ResponseEntity<Void>
        removeProductFromOrder(@PathVariable int orderId, @RequestBody Product product) {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {

                orderService.removeProductFromOrder(existingOrder, product);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping
        public ResponseEntity<List<Order>> getAllOrders() {
            List<Order> orders = orderService.getAllOrders();

            if (!orders.isEmpty()) {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/{productId}")
        public ResponseEntity<String> getProductInfo(@PathVariable int productId) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                product.displayProductInfo();
                return new ResponseEntity<>("Product info displayed in console.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/products")
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        @PostMapping("/products")
        public ResponseEntity<Void> addProduct(@RequestBody Product product) {
            productService.addProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @PutMapping("/products/{productId}")
        public ResponseEntity<Void>
        updateProduct(@PathVariable int productId, @RequestBody Product updatedProduct) {
            Product existingProduct = productService.getProductById(productId);

            if (existingProduct != null) {

                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());

                productService.updateProduct(existingProduct);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/products/{productId}")
        public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
            Product existingProduct = productService.getProductById(productId);

            if (existingProduct != null) {

                productService.deleteProduct(existingProduct);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @SneakyThrows
        @PostMapping("/create")
        public ResponseEntity<Order> createOrder(@RequestParam int orderId) {
            if (orderId <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Order createdOrder = orderService.createOrder(orderId);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        }

    }

}
