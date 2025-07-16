package com.yeisonmenau.citasmedicas.infrastructure.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {
    private String pacienteNombre;
    private LocalDate pacienteFechaNacimiento;
}
