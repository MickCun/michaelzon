package com.example.retail.Orders;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.retail.Customers.CustomerController;
import com.example.retail.Customers.CustomerService;
import com.example.retail.Products.*;

@Controller
public class OrderController {

    private OrderService orderService;
    private final CustomerController customerController;
    private ProductService productService;
    

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, CustomerController customerController, ProductService productService) {
        this.orderService = orderService;
        this.customerController = customerController;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        String custUsername = customerController.CustomerUsername;
        List<Order> orders = orderService.getOrdersByCustomerUsername(custUsername);
        model.addAttribute("userLogin", CustomerController.userLogin);
        model.addAttribute("orders", orders);
    return "orders";
}

// Admin Code
    @GetMapping("/admin-login")
    public String adminLogin(Principal principal, RedirectAttributes redirectAttributes) {
        Boolean isAdmin = CustomerController.isAdmin;

        if(isAdmin == true){
            return "redirect:/admin";
        } else {
            return "redirect:/invalidperms";
        }        
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("userLogin", CustomerController.userLogin);
        return "redirect:/admin-orders";
    }

    @GetMapping("/invalidperms")
    public String invalidPerms(Model model) {
        model.addAttribute("userLogin", CustomerController.userLogin);
        return "invalidperms";
    }

    @GetMapping("/admin-orders")
    public String adminOrders(Model model) {
        List<Order> orders = orderService.getOrders();
        model.addAttribute("userLogin", CustomerController.userLogin);
        model.addAttribute("orders", orders);
        return "adminorders";
    }

    @GetMapping("/orders/updateStatus")
    public String updateOrderStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        orderService.saveOrder(order);
        return "redirect:/admin-orders";
    }


    @GetMapping("/admin-products")
    public String adminProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("userLogin", CustomerController.userLogin);
        model.addAttribute("products", products);
        return "adminproducts";
    }

    @GetMapping("/add-new-product")
    public String addNewProduct(Model model) {
        model.addAttribute("userLogin", CustomerController.userLogin);
        return "adminNewProduct";
    }

    @PostMapping("/newProduct")
    public String addNewProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("image_url") String imageUrl, @RequestParam("price") Double price, @RequestParam("quantity") Integer quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);
        product.setQuantity(quantity);
        productService.saveProduct(product);
        return "redirect:/admin-products";
    }

    @GetMapping("/adminproduct/{id}")
    public String getProductById(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("userLogin", CustomerController.userLogin);
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            model.addAttribute("message", "Product not found");
        }
        return "adminUpdateProducts";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("image_url") String image_url, @RequestParam("price") Double price, @RequestParam("quantity") Integer quantity, @RequestParam("visibility") boolean visible) {
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(image_url);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setVisible(visible);

        productService.saveProduct(product);
        return "redirect:/admin-products";
    }
}