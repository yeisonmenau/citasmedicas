package com.yeisonmenau.citasmedicas.domain.repository;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteRequestDTO);
    PacienteResponseDTO guardarPaciente(PacienteEntity pacienteEntity);
    List<PacienteResponseDTO> buscarPacientes();
    Optional<PacienteResponseDTO> buscarPacientePorId(Long pacienteId);
    void eliminarPaciente(Long pacienteId);
}
