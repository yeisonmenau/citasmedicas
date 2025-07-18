package com.yeisonmenau.citasmedicas.application.service;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientePorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientesUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarPacienteUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarPacienteUseCase;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.domain.repository.PacienteRepository;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class PacienteService implements GuardarPacienteUseCase, BuscarPacientesUseCase, BuscarPacientePorIdUseCase, EliminarPacienteUseCase {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<PacienteResponseDTO> buscarPacientes() {
        return pacienteRepository.buscarPacientes();
    }

    @Override
    public void eliminarPaciente(Long pacienteId) {
        pacienteRepository.eliminarPaciente(pacienteId);
    }


    @Override
    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO paciente) {
        return pacienteRepository.guardarPaciente(paciente);
    }

    @Override
    public PacienteResponseDTO guardarPaciente(PacienteEntity paciente) {
        return pacienteRepository.guardarPaciente(paciente);
    }

    @Override
    public Optional<PacienteResponseDTO> buscarPacientePorId(Long pacienteId) {
        return pacienteRepository.buscarPacientePorId(pacienteId);
    }
}
