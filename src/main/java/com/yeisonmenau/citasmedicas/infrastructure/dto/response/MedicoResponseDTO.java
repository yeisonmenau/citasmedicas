package com.yeisonmenau.citasmedicas.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
@NoArgsConstructor
@Getter
@Setter
public class MedicoResponseDTO {
    private Long medicoId;
    private String medicoNombre;
    private String medicoEspecialidad;
    private Integer medicoEdad;

    public MedicoResponseDTO(Long medicoId, String medicoNombre, String medicoEspecialidad, LocalDate medicoFechaNacimiento){
        this.medicoId = medicoId;
        this.medicoNombre = medicoNombre;
        this.medicoEspecialidad = medicoEspecialidad;
        this.medicoEdad = Period.between(medicoFechaNacimiento, LocalDate.now()).getYears();
    }
}
