package com.example.retail.Customers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.retail.Products.Product;
import com.example.retail.Orders.*;

import jakarta.servlet.http.HttpSession; 

@Controller
public class CustomerController {

    public static long customerID = 0; 
    public static boolean loggedIn = false; 
    public static String accountName = null; 
    public static String CustomerUsername = null;
    public static String userLogin = displayLogin();
    public static Boolean isAdmin = false;  

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
    Customer customer = customerService.getCustomerById(customerID);
    if(customer == null){
        return "redirect:/login";
    }
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (customer.getProducts() == null) {
        customer.setProducts(new ArrayList<>());
    }
    if (cart != null && !cart.isEmpty()) {
        customer.getProducts().addAll(cart);
    }
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);

    for(Product p : cart){
        Order order = new Order();
        order.setProductId((int) p.getId());
        order.setDate(formattedDateTime);
        order.setUsername(customer.getUsername());
        order.setStatus("Pending");
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        order.setDescription(p.getDescription());
        order.setOrdered(p.getOrdered());

        // Save the order
        order.setCustomer(customer);
        orderService.saveOrder(order);
    }

    customerService.saveCustomer(customer);
    session.removeAttribute("cart");
    return "redirect:/orders";
}

    public static String displayLogin(){
        if(loggedIn == true){
            return accountName;
        } else {
            return "Login/Register";
        }
    }
    
    @GetMapping("/login")
    public String login(Model model ){
        model.addAttribute("userLogin", userLogin);
        if(loggedIn == true){
            return "redirect:/orders";
        } else {
            return "/login";
        }
    }

    @PostMapping("/login")
    public String loginToAccount(@RequestParam String username, @RequestParam String password){
        for(Customer customer : customerService.getAllCustomers()){
            if(customer.getUsername().equals(username) && customer.getPassword().equals(password)){
                loggedIn = true;
                accountName = customer.getFname();
                CustomerUsername = customer.getUsername();
                customerID = customer.getId();
                userLogin = displayLogin();
                isAdmin = customer.getAdmin();
                return "redirect:/";
            }
        }
        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userLogin", userLogin);
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(@RequestParam String fname, @RequestParam String lname, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String location){
        Customer customer = new Customer();
        // Check username is unique
        if(customerService.getCustomerByUsername(username) != null){
            return "redirect:/register";
        }
        // Check email is unique
        for(Customer c : customerService.getAllCustomers()){
            if(c.getEmail().equals(email)){
                return "redirect:/register";
            }
        }
        // Set the values for the new customer
        customer.setFname(fname);
        customer.setLname(lname);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setLocation(location);
        customer.setAdmin(false);
        customerService.saveCustomer(customer);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        loggedIn = false;
        accountName = null;
        userLogin = displayLogin();
        return "redirect:/";
    }
}


