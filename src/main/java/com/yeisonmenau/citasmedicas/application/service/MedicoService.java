package com.yeisonmenau.citasmedicas.application.service;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarMedicoPorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarMedicosUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarMedicoUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarMedicoUseCase;
import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MedicoService implements GuardarMedicoUseCase, BuscarMedicosUseCase, BuscarMedicoPorIdUseCase, EliminarMedicoUseCase {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Optional<Medico> buscarMedicoPorId(Long medicoId) {
        return medicoRepository.buscarMedicoPorId(medicoId);
    }

    @Override
    public List<Medico> buscarMedicos() {
        return medicoRepository.buscarMedicos();
    }

    @Override
    public void eliminarMedico(Long medicoId) {
        medicoRepository.eliminarMedico(medicoId);
    }

    @Override
    public Medico guardarMedico(Medico medico) {
        return medicoRepository.guardarMedico(medico);
    }
}
