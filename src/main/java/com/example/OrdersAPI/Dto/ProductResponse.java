package com.example.OrdersAPI.Dto;

import com.example.OrdersAPI.Model.Product;

import java.math.BigDecimal;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int stock;
    private BigDecimal price;
    public ProductResponse(Product p){
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.stock = p.getStock();
        this.price = p.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStockActual(int stock) {
        this.stock = stock;
    }

    public void setPrice(BigDecimal price) {
       this.price = price;
    }
}
