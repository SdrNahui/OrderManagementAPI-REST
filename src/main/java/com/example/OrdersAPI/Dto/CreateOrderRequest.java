package com.example.OrdersAPI.Dto;

import jakarta.validation.constraints.NotBlank;

public class CreateOrderRequest {
    @NotBlank
    private String customerName;

    public CreateOrderRequest(){
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
