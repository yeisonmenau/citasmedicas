package com.yeisonmenau.citasmedicas.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaMedicaResponseDTO {
    private Long citaMedicaId;
    private String medicoNombre;
    private String pacienteNombre;
    private String motivo;
    private LocalDate citaMedicaFecha;
    private LocalTime citaMedicaHora;
}
