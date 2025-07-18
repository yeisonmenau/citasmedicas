package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;

import java.util.List;

public interface BuscarPacientesUseCase {
    List<PacienteResponseDTO> buscarPacientes();
}
