package com.example.OrdersAPI.Exception;

import com.example.OrdersAPI.Model.StatusOrder;

public class IllegalStatusException extends RuntimeException {
    public IllegalStatusException(StatusOrder statusOrder) {
        super("No se puede cambiar o confirmar una orden con el estado " + statusOrder);
    }
}
