package com.yeisonmenau.citasmedicas.domain.exception;

public class MedicoNoEncontradoException extends RuntimeException {
    public MedicoNoEncontradoException(Long medicoId) {
        super("Medico con ID: "+medicoId+" no encontrado");
    }
}
