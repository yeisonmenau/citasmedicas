package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;

import java.util.List;

public interface BuscarPacientesUseCase {
    List<Paciente> buscarPacientes();
}
