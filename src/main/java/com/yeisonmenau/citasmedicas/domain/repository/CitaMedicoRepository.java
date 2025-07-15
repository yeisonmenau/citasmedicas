package com.yeisonmenau.citasmedicas.domain.repository;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;

import java.util.List;
import java.util.Optional;

public interface CitaMedicoRepository {
    CitaMedica guardarCitaMedica (CitaMedica citaMedica);
    List<CitaMedica> buscarCitasMedicas();
    Optional<CitaMedica> buscarCitaMedicaPorId(Long citaMedicaId);
    void eliminarCitaMedica();
}
