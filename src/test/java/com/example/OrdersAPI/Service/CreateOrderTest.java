package com.example.OrdersAPI.Service;

import com.example.OrdersAPI.Dto.OrderResponse;
import com.example.OrdersAPI.Exception.BusinessException;
import com.example.OrdersAPI.Model.Order;
import com.example.OrdersAPI.Model.StatusOrder;
import com.example.OrdersAPI.Repository.OrderRepository;
import com.example.OrdersAPI.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrderTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_succes(){
        Order orderToCreate = new Order();
        orderToCreate.setCustomerName("juan");


        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setCustomerName("juan");
        savedOrder.setItemList(new ArrayList<>());
        savedOrder.setTotal(BigDecimal.ZERO);
        savedOrder.setStatusOrder(StatusOrder.CREATED);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        //
        Order result = orderService.createOrder(orderToCreate);

        assertNotNull(result, "el resultado es null -> el mock no se esta usando");
    //    assertEquals(1L, result.getId());
        assertEquals(StatusOrder.CREATED, result.getStatusOrder());
        assertEquals(BigDecimal.ZERO, result.getTotal());
        verify(orderRepository, times(1)).save(any(Order.class));

    }
    @Test
    void createOrder_noFound(){
        Order orderToSave = new Order();
        assertThrows(BusinessException.class, () -> {
            orderService.createOrder(orderToSave);
        });
        verify(orderRepository, never()).save(any(Order.class));
    }
}
