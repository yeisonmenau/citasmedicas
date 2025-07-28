package com.yeisonmenau.citasmedicas.infrastructure.mapper;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.CitaMedicaRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.CitaMedicaResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.CitaMedicaEntity;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.repository.JpaMedicoRepository;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.repository.JpaPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CitaMedicaMapperPersonalizado {
    private final JpaPacienteRepository jpaPacienteRepository;
    private final JpaMedicoRepository jpaMedicoRepository;

    public CitaMedicaResponseDTO dominioADTO (CitaMedica citaMedica){
        return new CitaMedicaResponseDTO(
                citaMedica.getCitaMedicaId(),
                citaMedica.getMedico().getMedicoNombre(),
                citaMedica.getPaciente().getPacienteNombre(),
                citaMedica.getMotivo(),
                citaMedica.getCitaMedicaFecha(),
                citaMedica.getCitaMedicaHora()
        );
    }
    public CitaMedicaEntity dtoAEntidad (CitaMedicaRequestDTO dto){
        return new CitaMedicaEntity(null,
                jpaMedicoRepository.findById(dto.getMedicoId()).orElseThrow(() -> new RuntimeException("Medico no encontrado")),
                jpaPacienteRepository.findById(dto.getPacienteId()).orElseThrow(() -> new RuntimeException("Paciente no encontrado")),
                dto.getMotivo(),
                dto.getCitaMedicaFecha(),
                dto.getCitaMedicaHora());
    }

}
