package com.example.OrdersAPI.Exception;

public class IllegalProductException extends RuntimeException {
    public IllegalProductException() {
        super("Tiene que solicitar al menos un producto");
    }
}
