package com.innowave.cursomc.services.exceptions;

public class AuthorizaationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AuthorizaationException(String msg){
        super(msg);

    }

    public AuthorizaationException(String msg, Throwable cause){
        super(msg, cause);
    }
}
