package com.yeisonmenau.citasmedicas.infrastructure.persistence.repository;

import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Long> {
}
