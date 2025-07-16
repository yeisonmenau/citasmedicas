package com.yeisonmenau.citasmedicas.infrastructure.persistence;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "cita_medica")
public class CitaMedicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citaMedicaId;
    private Medico medico;
    private Paciente paciente;
    private String motivo;
    private LocalDate citaMedicaFecha;
    private LocalTime citaMedicaHora;
}
