package com.example.retail.Orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retail.Customers.Customer;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order setCustomerForOrder(Order order, Customer customer) {
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomerUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public Order updateOrderStatus(Order order, String status) {
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

}