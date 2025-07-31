package com.example.retail.Products;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

    public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
    
    public Product getProductById(long id){
        return productRepository.findById(id).orElse(null);
    }

	public List<Product> getProductsByIds(List<Long> productIds) {
		return productRepository.findAllById(productIds);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}
}

