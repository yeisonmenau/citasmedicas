package com.yeisonmenau.citasmedicas.domain.repository;

import com.yeisonmenau.citasmedicas.domain.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    Medico guardarMedico(Medico medico);
    List<Medico> buscarMedicos();
    Optional<Medico> buscarMedicoPorId(Long medicoId);
    void eliminarMedico(Long medicoId);
}
