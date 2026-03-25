package com.irvings.order;

public class OrderAlreadyInProgressException extends Exception {
    public OrderAlreadyInProgressException(String message) {
        super(message);
    }
}

