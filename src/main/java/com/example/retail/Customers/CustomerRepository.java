package com.example.retail.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Table;


@Repository
@Table(name = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);   
}