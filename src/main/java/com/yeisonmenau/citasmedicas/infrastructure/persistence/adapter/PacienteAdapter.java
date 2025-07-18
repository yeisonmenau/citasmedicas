package com.yeisonmenau.citasmedicas.infrastructure.persistence.adapter;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.domain.repository.PacienteRepository;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.PacienteMapper;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.repository.JpaPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PacienteAdapter implements PacienteRepository {

    private final JpaPacienteRepository jpaPacienteRepository;
    private final PacienteMapper mapper;

    @Autowired
    public PacienteAdapter(JpaPacienteRepository jpaPacienteRepository, PacienteMapper mapper) {
        this.jpaPacienteRepository = jpaPacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = mapper.dtoADominio(pacienteRequestDTO);
        PacienteEntity entidad = mapper.dominioAEntidad(paciente);
        PacienteEntity guardado = jpaPacienteRepository.save(entidad);
        PacienteResponseDTO respuesta = new PacienteResponseDTO(
                guardado.getPacienteId(),
                guardado.getPacienteNombre(),
                guardado.getPacienteFechaNacimiento());
        return respuesta;
    }

    @Override
    public PacienteResponseDTO guardarPaciente(PacienteEntity pacienteEntity) {
        PacienteEntity guardado = jpaPacienteRepository.save(pacienteEntity);
        PacienteResponseDTO respuesta = new PacienteResponseDTO(
                guardado.getPacienteId(),
                guardado.getPacienteNombre(),
                guardado.getPacienteFechaNacimiento());
        return respuesta;
    }

    @Override
    public List<PacienteResponseDTO> buscarPacientes() {
        List<Paciente> pacientes = mapper.entidadesADominios(jpaPacienteRepository.findAll());
        List<PacienteResponseDTO> pacientesRespuesta = pacientes
                .stream()
                .map(paciente -> new PacienteResponseDTO(
                        paciente.getPacienteId(),
                        paciente.getPacienteNombre(),
                        paciente.getPacienteFechaNacimiento())).toList();
        return pacientesRespuesta;
    }

    @Override
    public Optional<PacienteResponseDTO> buscarPacientePorId(Long pacienteId) {
        return jpaPacienteRepository.findById(pacienteId)
                .map(mapper::entidadADominio)
                .map(paciente -> new PacienteResponseDTO(
                        paciente.getPacienteId(),
                        paciente.getPacienteNombre(),
                        paciente.getPacienteFechaNacimiento()
                ));
    }

    @Override
    public void eliminarPaciente(Long pacienteId) {
        jpaPacienteRepository.deleteById(pacienteId);
    }
}