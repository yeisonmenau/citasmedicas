package com.yeisonmenau.citasmedicas.infrastructure.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicoResponseDTO {
    private Long medicoId;
    private String medicoNombre;
    private String medicoEspecialidad;
    private Integer medicoEdad;
}
