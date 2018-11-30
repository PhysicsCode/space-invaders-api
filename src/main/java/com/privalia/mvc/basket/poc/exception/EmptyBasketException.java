package com.privalia.mvc.basket.poc.exception;

import org.springframework.core.NestedRuntimeException;

public class EmptyBasketException extends NestedRuntimeException {

    public EmptyBasketException(String msg) {
        super(msg);
    }
}
