package com.yeisonmenau.citasmedicas.infrastructure.mapper;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.MedicoRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.MedicoResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.MedicoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    // Controller → Dominio
    Medico dtoADominio(MedicoRequestDTO dto);

    // Dominio → Controller (respuesta)
    MedicoResponseDTO dominioADTO(Medico paciente);

    // Dominio → Entity (guardar en DB)
    MedicoEntity dominioAEntidad(Medico paciente);

    // Entity → Dominio (leer de DB)
    Medico entidadADominio(MedicoEntity entity);

    // Listados
    List<MedicoResponseDTO> dominioADTOs(List<Medico> pacientes);
    List<Medico> entidadesADominios(List<MedicoEntity> entities);
}
