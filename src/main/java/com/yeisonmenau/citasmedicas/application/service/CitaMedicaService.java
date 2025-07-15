package com.yeisonmenau.citasmedicas.application.service;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarCitaMedicaPorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarCitasMedicasUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarCitaMedicaUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarCitaMedicaUseCase;
import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;
import com.yeisonmenau.citasmedicas.domain.repository.CitaMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService implements GuardarCitaMedicaUseCase, BuscarCitasMedicasUseCase, BuscarCitaMedicaPorIdUseCase, EliminarCitaMedicaUseCase {
    private final CitaMedicoRepository citaMedicoRepository;
    @Autowired
    public CitaMedicaService(CitaMedicoRepository citaMedicoRepository) {
        this.citaMedicoRepository = citaMedicoRepository;
    }

    @Override
    public Optional<CitaMedica> buscarCitaMedicaPorId(Long citaMedicaId) {
        return citaMedicoRepository.buscarCitaMedicaPorId(citaMedicaId);
    }

    @Override
    public List<CitaMedica> buscarCitasMedicas() {
        return citaMedicoRepository.buscarCitasMedicas();
    }

    @Override
    public void eliminarCitaMedica(Long citaMedicaId) {

    }

    @Override
    public CitaMedica guardarCitaMedica(CitaMedica citaMedica) {
        return null;
    }
}
