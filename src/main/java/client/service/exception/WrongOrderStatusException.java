package client.service.exception;

import client.data.model.enums.OrderStatus;

public class WrongOrderStatusException extends IllegalArgumentException {
    public WrongOrderStatusException(Long id, OrderStatus status) {
        super(String.format("Order with id [%s] has [%s] status", id, status.toString()));
    }
}
