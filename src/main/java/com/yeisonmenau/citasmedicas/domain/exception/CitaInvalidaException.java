package com.yeisonmenau.citasmedicas.domain.exception;

public class CitaInvalidaException extends RuntimeException {
    public CitaInvalidaException(String message) {
        super("Cita inválida: "+message);
    }
}
