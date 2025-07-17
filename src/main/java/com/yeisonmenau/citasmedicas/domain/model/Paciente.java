package com.yeisonmenau.citasmedicas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Paciente {
    private final Long pacienteId;
    private final String pacienteNombre;
    private final LocalDate pacienteFechaNacimiento;
}
