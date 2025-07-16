package com.yeisonmenau.citasmedicas.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor(force = true)
public class Paciente {
    private final Long pacienteId;
    private final String pacienteNombre;
    private final LocalDate pacienteFechaNacimiento;
}
