package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;

import java.util.Optional;

public interface BuscarCitaMedicaPorIdUseCase {
    Optional<CitaMedica> buscarCitaMedicaPorId(Long citaMedicaId);
}
