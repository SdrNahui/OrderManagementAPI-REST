package com.example.OrdersAPI.Repository;

import com.example.OrdersAPI.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
