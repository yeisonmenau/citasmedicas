package com.yeisonmenau.citasmedicas.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
@NoArgsConstructor
@Getter
@Setter
public class PacienteResponseDTO {
    private Long pacienteId;
    private String pacienteNombre;
    private Integer pacienteEdad;

    public PacienteResponseDTO (Long pacienteId, String pacienteNombre, LocalDate pacienteFechaNacimiento){
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.pacienteEdad = Period.between(pacienteFechaNacimiento, LocalDate.now()).getYears();
    }

}
