package com.yeisonmenau.citasmedicas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedica {
    private Long citaMedicaId;
    private Medico medico;
    private Paciente paciente;
    private String motivo;
    private LocalDate citaMedicaFecha;
    private LocalTime citaMedicaHora;
}
