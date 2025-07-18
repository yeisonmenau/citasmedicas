package com.yeisonmenau.citasmedicas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    private Long pacienteId;
    private String pacienteNombre;
    private LocalDate pacienteFechaNacimiento;
}
