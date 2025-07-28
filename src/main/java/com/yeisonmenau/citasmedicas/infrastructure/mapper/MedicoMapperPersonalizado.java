package com.yeisonmenau.citasmedicas.infrastructure.mapper;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.MedicoResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class MedicoMapperPersonalizado {
    public MedicoResponseDTO dominioADTO (Medico medico){
        return new MedicoResponseDTO(
                medico.getMedicoId(),
                medico.getMedicoNombre(),
                medico.getMedicoEspecialidad(),
                Period.between(medico.getMedicoFechaNacimiento(), LocalDate.now()).getYears()
        );
    }
}
