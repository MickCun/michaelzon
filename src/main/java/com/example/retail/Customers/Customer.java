package com.example.retail.Customers;

import java.util.List;

import com.example.retail.Products.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id; 
    private String fname;
    private String lname;
    private String email;
    private String location; 
    private String password; 
    private String username;
    private Boolean admin;
    
    @ManyToMany
    private List<Product> products;

    // Null constructor
    public Customer() {
    }

    // Constructor
    public Customer(String fname, String lname, String email, String location, String password, String username, Boolean admin) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.location = location;
        this.password = password;
        this.username = username;
        this.admin = admin;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }
    

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}