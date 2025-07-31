package com.example.retail.Orders;
import java.util.List;

import com.example.retail.Customers.Customer;
import com.example.retail.Products.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    
    @Column(name = "product_id") 
    private int productId; 
    
    private String date; 
    private String username; 
    private String status;
    private String name;
    private Double price; 
    private String description; 
    private int ordered; 

    // Null constructor
    public Order() {
    }

    // Constructor
    public Order(String date, String status, String username, int productId, long id, String name, Double price, String description, Integer ordered) {
        this.id = id;
        this.productId = productId;
        this.username = username;
        this.date = date;
        this.status = status;
        this.name = name;
        this.price = price;
        this.description = description;
        this.ordered = ordered;
    }

    @ManyToMany
    private List<Product> products;
    
    @ManyToOne 
    private Customer customer; 

    // Getters and setters    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", Status='" + status + '\'' +
                ", productId=" + productId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", ordered='" + ordered + '\'' +
                '}';
    }
}