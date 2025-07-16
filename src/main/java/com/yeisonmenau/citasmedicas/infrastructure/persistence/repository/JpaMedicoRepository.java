package com.yeisonmenau.citasmedicas.infrastructure.persistence.repository;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, Long>{
}
