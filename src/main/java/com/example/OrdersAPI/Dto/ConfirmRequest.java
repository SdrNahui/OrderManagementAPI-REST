package com.example.OrdersAPI.Dto;

public class ConfirmRequest {
    private boolean confirm;
    
    public ConfirmRequest() {
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
