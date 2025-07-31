package com.example.retail.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private long id; 
    private String name;
    private String description;
    private double price;
    private Integer quantity;
    private String imageUrl; 
    public int ordered; 
    public boolean visible; 


// Null Constructor: 
public Product(){
}

public Product(long id, String name, String description, double price, Integer quantity, String imageUrl, int ordered, boolean visible){
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.imageUrl = imageUrl;
    this.ordered = ordered;
    this.visible = visible;
}

// Getters & Setters:
public long getId(){
    return id;
}

public void setId(long id){
    this.id = id;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name = name;
}

public String getDescription(){
    return description;
}

public void setDescription(String description){
    this.description = description;
}

public double getPrice(){
    return price;
}

public void setPrice(double price){
    this.price = price;
}

public Integer getQuantity(){
    return quantity;
}

public void setQuantity(Integer quantity){
    this.quantity = quantity;
}

public String getImageUrl(){
    return imageUrl;
}

public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}

public int getOrdered(){
    return ordered;
}

public void setOrdered(int ordered){
    this.ordered = ordered;
}

public boolean isVisible(){
    return visible;
}

public void setVisible(boolean visible){
    this.visible = visible;
}

// toString method:
@Override
public String toString(){
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            ", ordered=" + ordered +
            ", visible=" + visible +
            '}';
}
}