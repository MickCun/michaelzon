package com.example.retail.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Table;


@Repository
@Table(name = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {   
    
}