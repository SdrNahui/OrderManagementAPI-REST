package com.example.OrdersAPI.Dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    String description;
    @PositiveOrZero
    private int stock;
    @Positive
    private BigDecimal price;

    public ProductRequest(){
    }
    public ProductRequest(String name, String description, int stock, BigDecimal price){
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
