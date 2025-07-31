package com.example.retail.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.retail.Customers.Customer;

import jakarta.persistence.Table;


@Repository
@Table(name = "orders")
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Customer customer);
    List<Order> findByUsername(String username);
}