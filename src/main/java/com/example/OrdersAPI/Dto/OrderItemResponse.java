package com.example.OrdersAPI.Dto;

import com.example.OrdersAPI.Model.OrderItem;


import java.math.BigDecimal;

public class OrderItemResponse {
    private Long id;
    private String itemName;
    private String itemDescription;
    private int amount;
    private BigDecimal price;
    private Long orderId;

    public OrderItemResponse(OrderItem orderItem){
        this.id = orderItem.getId();
        this.itemName = orderItem.getItemName();
        this.itemDescription = orderItem.getItemDescription();
        this.amount = orderItem.getAmount();
        this.price = orderItem.getPrice();
        this.orderId = orderItem.getOrder().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
