package com.yeisonmenau.citasmedicas.infrastructure.mapper;

import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PacienteMapper {
    // Controller → Dominio
    Paciente dtoADominio(PacienteRequestDTO dto);

    // Dominio → Controller (respuesta)
    PacienteResponseDTO dominioADTO(Paciente paciente);

    // Dominio → Entity (guardar en DB)
    PacienteEntity dominioAEntidad(Paciente paciente);

    // Entity → Dominio (leer de DB)
    Paciente entidadADominio(PacienteEntity entity);

    // Listados
    List<PacienteResponseDTO> dominioADTOs(List<Paciente> pacientes);
    List<Paciente> entidadesADominios(List<PacienteEntity> entities);

}
