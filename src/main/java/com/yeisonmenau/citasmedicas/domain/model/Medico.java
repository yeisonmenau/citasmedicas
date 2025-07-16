package com.yeisonmenau.citasmedicas.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor(force = true)
public class Medico {
    private final Long medicoId;
    private final String medicoNombre;
    private final String medicoEspecialidad;
    private final LocalDate medicoFechaNacimiento;
}
