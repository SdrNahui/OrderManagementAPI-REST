package com.example.OrdersAPI.Exception;

public class NoFoundException extends RuntimeException {
    public NoFoundException(Long id) {
        super("No se encontro producto u orden con la siguiente id: " + id);
    }
}
