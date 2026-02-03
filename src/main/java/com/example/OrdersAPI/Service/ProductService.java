package com.example.OrdersAPI.Service;

import com.example.OrdersAPI.Dto.ProductRequest;
import com.example.OrdersAPI.Dto.ProductResponse;
import com.example.OrdersAPI.Exception.NoFoundException;
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
    public Product createProduct(ProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, ProductRequest productUpdate){
        return productRepository.findById(id).map(exist -> {
           exist.setName(productUpdate.getName());
           exist.setDescription(productUpdate.getDescription());
           exist.setStock(productUpdate.getStock());
           exist.setPrice(productUpdate.getPrice());
           return productRepository.save(exist);
        });
    }
     public void deleteProduct(Long id){
        if(!productRepository.existsById(id)) {
            throw new NoFoundException(id);
        }
        productRepository.deleteById(id);
     }

}
