package com.example.OrdersAPI.Controller;

import com.example.OrdersAPI.Dto.ConfirmRequest;
import com.example.OrdersAPI.Dto.CreateOrderRequest;
import com.example.OrdersAPI.Dto.OrderResponse;
import com.example.OrdersAPI.Dto.ProductOrderRequest;
import com.example.OrdersAPI.Model.Order;
import com.example.OrdersAPI.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> orderResponses = orderService.getAllOrders().stream().map(OrderResponse::new).toList();
        return ResponseEntity.ok(orderResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse>getOrderById(@PathVariable Long id){
        return  orderService.getOrderById(id).map(OrderResponse::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request){
        Order oSave = orderService.createOrder(new Order(request.getCustomerName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(oSave));
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<OrderResponse> addItem(@Valid @RequestBody List<@Valid ProductOrderRequest> request,
                                                 @PathVariable Long id){
        Order order = orderService.addItem(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponse(order));
    }
    @PostMapping("/remove/{id}")
    public ResponseEntity<OrderResponse> removeItem(@PathVariable Long id,
                                                    @Valid @RequestBody List<@Valid ProductOrderRequest> request){
        Order order = orderService.removeItem(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponse(order));
    }
    @PostMapping("/confirm/{id}")
    public ResponseEntity<OrderResponse> confirmOrder(@RequestBody ConfirmRequest request, @PathVariable Long id){
        Order order = orderService.confirmOrder(id, request.isConfirm());
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponse(order));
    }
    @PostMapping("/cancel/{id}")
    public ResponseEntity<OrderResponse> cancelOrder(@RequestBody ConfirmRequest request, @PathVariable Long id){
        Order order = orderService.cancelOrder(id, request.isConfirm());
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponse(order));
    }
}
