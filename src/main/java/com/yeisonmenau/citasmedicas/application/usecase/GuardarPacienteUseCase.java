package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;

public interface GuardarPacienteUseCase {
    PacienteResponseDTO guardarPaciente(PacienteRequestDTO paciente);
    PacienteResponseDTO guardarPaciente(PacienteEntity paciente);
}
