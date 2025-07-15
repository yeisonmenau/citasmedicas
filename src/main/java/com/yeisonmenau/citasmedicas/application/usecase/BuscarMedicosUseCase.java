package com.yeisonmenau.citasmedicas.application.usecase;

import com.yeisonmenau.citasmedicas.domain.model.Medico;

import java.util.List;

public interface BuscarMedicosUseCase {
    List<Medico> buscarMedicos();
}
