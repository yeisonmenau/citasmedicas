package com.yeisonmenau.citasmedicas.domain.repository;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    Paciente guardarPaciente(Paciente paciente);
    List<Paciente> buscarPacientes();
    Optional<Paciente> buscarPacientePorId(Long pacienteId);
    void eliminarPaciente(Long pacienteId);
}
