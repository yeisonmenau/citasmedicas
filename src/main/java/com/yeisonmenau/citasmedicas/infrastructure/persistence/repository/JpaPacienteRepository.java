package com.yeisonmenau.citasmedicas.infrastructure.persistence.repository;

import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPacienteRepository extends JpaRepository<PacienteEntity, Long> {
}
