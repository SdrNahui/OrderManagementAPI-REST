package com.example.OrdersAPI.Dto;

import com.example.OrdersAPI.Model.Order;
import com.example.OrdersAPI.Model.StatusOrder;

import java.math.BigDecimal;
import java.util.List;


public class OrderResponse {
    private Long id;
    private StatusOrder statusOrder;
    private String customerName;
    private List<OrderItemResponse> list;
    private BigDecimal total;

    public OrderResponse(Order order){
        this.customerName = order.getCustomerName();
        this.statusOrder = order.getStatusOrder();
        this.id = order.getId();
        this.list = order.getItemList().stream().map(OrderItemResponse::new).toList();
        this.total = order.getTotal();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItemResponse> getList() {
        return list;
    }

    public void setList(List<OrderItemResponse> list) {
        this.list = list;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
