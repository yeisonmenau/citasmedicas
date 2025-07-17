package com.yeisonmenau.citasmedicas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CitaMedica {
    private final Long citaMedicaId;
    private final Medico medico;
    private final Paciente paciente;
    private final String motivo;
    private final LocalDate citaMedicaFecha;
    private final LocalTime citaMedicaHora;
}
