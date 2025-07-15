package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;

import java.util.Optional;

public interface BuscarPacientePorIdUseCase {
    Optional<Paciente> buscarPacientePorId(Long pacienteId);
}
