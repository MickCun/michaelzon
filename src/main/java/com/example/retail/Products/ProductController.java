package com.example.retail.Products;
import com.example.retail.Customers.Customer;
import com.example.retail.Customers.CustomerController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
        
    @GetMapping("/")
    public String indexProductController(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        List<Product> products = new ArrayList<Product>();

        for(Product a : allProducts){
            if(a.isVisible() == true){
                products.add(a);
            }
        }

        model.addAttribute("userLogin", CustomerController.userLogin);
        if (!products.isEmpty()) {
            model.addAttribute("products", products);
        } else {
            model.addAttribute("message", "No products found");
        }
        return "index";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("userLogin", CustomerController.userLogin);
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            model.addAttribute("message", "Product not found");
        }
        return "product_info";
    }

    ArrayList<Long> idInCart = new ArrayList<Long>();
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") long productId, HttpSession session, RedirectAttributes redirectAttributes) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }
            if(idInCart.contains(productId)){
                return "redirect:/";
            }
            cart.add(product);
            totalCalculator(session);
            idInCart.add(productId);
            session.setAttribute("cart", cart);
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("userLogin", CustomerController.userLogin);
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        double total = totalCalculator(session); 
        double totalCart = totalCart(session); 

        if (cart != null && !cart.isEmpty()) {
            model.addAttribute("cart", cart);
            model.addAttribute("total", total);
            model.addAttribute("totalCart", totalCart);
        }
        return "cart";
    }

    public double totalCalculator(HttpSession session){
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        double total = 0; 
        if(cart == null){
            return total; 
        }
        for(int i = 0; i < cart.size(); i++){
            Product priceFinder = cart.get(i); 
            double priceOfProduct = priceFinder.getPrice(); 
            double quantityOfProduct = priceFinder.getOrdered();
            if(quantityOfProduct == 0){ 
                quantityOfProduct = 1; 
            }
            total += priceOfProduct * quantityOfProduct;
        }
        session.setAttribute("cart", cart);
        return total;
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(HttpSession session, @PathVariable("id") long productId){
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        for(int i = 0; i < cart.size(); i++){
            Product productFinder = cart.get(i); 
            long productFinderId = productFinder.getId();
            
            if(productFinderId == productId){
                cart.remove(productFinder); 
                idInCart.remove(productId); 
            }   
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
    

    @GetMapping("/cart/increase/{id}")
    public String increaseProductOrder(HttpSession session, @PathVariable("id") long productId) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getId() == productId){
                int noOrdered = cart.get(i).getOrdered() + 1; 
                System.out.println(noOrdered);
                cart.get(i).setOrdered(noOrdered);
                
                break; 
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
    
    @GetMapping("/cart/decrease/{id}")
    public String decreaseProductOrder(HttpSession session, @PathVariable("id") long productId) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getId() == productId){
                int noOrdered = cart.get(i).getOrdered() - 1; 
                if (noOrdered <= 0) {
                    noOrdered = 1; 
                }
                cart.get(i).setOrdered(noOrdered);
                break;
            }
        }
        session.setAttribute("cart", cart); 
        return "redirect:/cart";
    }

public int totalCart(HttpSession session){
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    int totalCart = 0; 
    if(cart == null){
        return totalCart; 
    }
    totalCart = cart.size();
    session.setAttribute("cart", cart);
    System.out.println(cart);
    return totalCart;
}
}