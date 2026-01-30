package com.example.OrdersAPI.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductOrderRequest {
    @NotNull
    private Long idProduct;
    @Min(1)
    @NotNull
    private Integer amount;

    public ProductOrderRequest(){
    }

    public ProductOrderRequest(Long idProduct, Integer amount){
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
