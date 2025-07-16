package com.yeisonmenau.citasmedicas.infrastructure.mapper;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.CitaMedicaRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.CitaMedicaResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.CitaMedicaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "sprig")
public interface CitaMedicaMapper {
    // Controller → Dominio
    CitaMedica dtoADominio(CitaMedicaRequestDTO dto);

    // Dominio → Controller (respuesta)
    CitaMedicaResponseDTO dominioADTO(CitaMedica paciente);

    // Dominio → Entity (guardar en DB)
    CitaMedicaEntity dominioAEntidad(CitaMedica paciente);

    // Entity → Dominio (leer de DB)
    CitaMedica entidadADominio(CitaMedicaEntity entity);

    // Listados
    List<CitaMedicaResponseDTO> dominioADTOs(List<CitaMedica> pacientes);
    List<CitaMedica> entidadesADominios(List<CitaMedicaEntity> entities);
}
