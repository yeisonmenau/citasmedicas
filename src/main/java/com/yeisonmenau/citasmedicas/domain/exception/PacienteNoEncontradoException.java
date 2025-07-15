package com.yeisonmenau.citasmedicas.domain.exception;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long pacienteId) {
        super("Paciente con ID: "+pacienteId+" no encontrado");
    }
}
