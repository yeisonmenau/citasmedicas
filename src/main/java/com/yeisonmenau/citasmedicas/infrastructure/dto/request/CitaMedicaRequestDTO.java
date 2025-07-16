package com.yeisonmenau.citasmedicas.infrastructure.dto.request;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaMedicaRequestDTO {
    private Long medicoId;
    private Long pacienteId;
    private String motivo;
    private LocalDate citaMedicaFecha;
    private LocalTime citaMedicaHora;
}
