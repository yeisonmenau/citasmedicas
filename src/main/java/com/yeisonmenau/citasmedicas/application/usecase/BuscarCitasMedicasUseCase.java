package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;

import java.util.List;

public interface BuscarCitasMedicasUseCase {
    List<CitaMedica> buscarCitasMedicas();
}
