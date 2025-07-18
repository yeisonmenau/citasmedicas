package com.yeisonmenau.citasmedicas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    private Long medicoId;
    private String medicoNombre;
    private String medicoEspecialidad;
    private LocalDate medicoFechaNacimiento;
}
