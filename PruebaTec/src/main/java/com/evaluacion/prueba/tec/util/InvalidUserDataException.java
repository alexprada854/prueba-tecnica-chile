package com.evaluacion.prueba.tec.util;

@SuppressWarnings("serial")
public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException(String message, Throwable cause) {
        super(message, cause);
    }
}