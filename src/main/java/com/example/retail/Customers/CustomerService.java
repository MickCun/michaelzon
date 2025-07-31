package com.example.retail.Customers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retail.Products.Product;
import com.example.retail.Products.ProductRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository; 
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Customer getCustomerById(long id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer clearCustomerOrder(Customer customer) {
        customer.setProducts(null);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Customer getCustomerByUsername(String accountName) {
        return customerRepository.findByUsername(accountName);
    }
}

