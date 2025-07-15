package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Medico;

import java.util.Optional;

public interface BuscarMedicoPorIdUseCase {
    Optional<Medico> buscarMedicoPorId(Long medicoId);
}
