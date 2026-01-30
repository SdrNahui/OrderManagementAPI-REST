package com.example.OrdersAPI.Service;

import com.example.OrdersAPI.Model.Product;
import com.example.OrdersAPI.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product productUpdate){
        return productRepository.findById(id).map(exist -> {
           exist.setName(productUpdate.getName());
           exist.setDescription(productUpdate.getDescription());
           exist.setStock(productUpdate.getStock());
           exist.setPrice(productUpdate.getPrice());
           return productRepository.save(exist);
        });
    }
     public boolean deleteProduct(Long id){
        if(!productRepository.existsById(id)) {
            return false;
        }
     return true;
     }

}
