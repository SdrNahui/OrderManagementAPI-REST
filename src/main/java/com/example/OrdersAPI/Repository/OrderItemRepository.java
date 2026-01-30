package com.example.OrdersAPI.Repository;

import com.example.OrdersAPI.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
