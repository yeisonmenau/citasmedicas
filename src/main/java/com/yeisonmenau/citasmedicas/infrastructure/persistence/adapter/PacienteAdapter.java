package com.yeisonmenau.citasmedicas.infrastructure.persistence.adapter;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.domain.repository.PacienteRepository;
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
    public Paciente guardarPaciente(Paciente paciente) {
        PacienteEntity entidad = mapper.dominioAEntidad(paciente);
        PacienteEntity guardado = jpaPacienteRepository.save(entidad);
        return mapper.entidadADominio(guardado);
    }

    @Override
    public List<Paciente> buscarPacientes() {
        return mapper.entidadesADominios(jpaPacienteRepository.findAll());
    }

    @Override
    public Optional<Paciente> buscarPacientePorId(Long pacienteId) {
        return jpaPacienteRepository.findById(pacienteId)
                .map(mapper::entidadADominio);
    }

    @Override
    public void eliminarPaciente(Long pacienteId) {
        jpaPacienteRepository.deleteById(pacienteId);
    }
}