package com.teste.castgroup.core.conta.model.exception;

public class ContaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ContaException (String message) {
        super(message);
    }
}
