package com.example.OrdersAPI.Controller;

import com.example.OrdersAPI.Dto.ErrorByMe;
import com.example.OrdersAPI.Dto.ProductResponse;
import com.example.OrdersAPI.Dto.ProductRequest;
import com.example.OrdersAPI.Model.Product;
import com.example.OrdersAPI.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        Product pSave = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(pSave));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> list = productService.getAllProduct().stream().map(ProductResponse::new).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse>getProductById(@PathVariable Long id){
        return productService.getProductById(id).map(ProductResponse::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductRequest productUpdate){
        return productService.updateProduct(id, productUpdate).map(ProductResponse::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorByMe> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
