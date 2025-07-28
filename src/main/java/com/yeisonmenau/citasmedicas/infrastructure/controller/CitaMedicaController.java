package com.yeisonmenau.citasmedicas.infrastructure.controller;

import com.yeisonmenau.citasmedicas.application.service.CitaMedicaService;
import com.yeisonmenau.citasmedicas.application.service.MedicoService;
import com.yeisonmenau.citasmedicas.application.service.PacienteService;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarCitaMedicaPorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarCitasMedicasUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarCitaMedicaUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarCitaMedicaUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarMedicoPorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientePorIdUseCase;
import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;
import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.CitaMedicaRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.CitaMedicaResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.MedicoResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.CitaMedicaMapper;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.CitaMedicaMapperPersonalizado;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.CitaMedicaEntity;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/citas-medicas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class CitaMedicaController {

    private final CitaMedicaMapper mapper;
    private final CitaMedicaMapperPersonalizado mapperPersonalizado;
    private final CitaMedicaService citaMedicaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<?> crearCitaMedica (CitaMedicaRequestDTO citaMedicaRequestDTO){
        CitaMedicaEntity citaMedicaEntidad = mapperPersonalizado.dtoAEntidad(citaMedicaRequestDTO);
        CitaMedica citaMedica = mapper.entidadADominio(citaMedicaEntidad);
        CitaMedica citaMedicaGuardada =  citaMedicaService.guardarCitaMedica(citaMedica);
        CitaMedicaResponseDTO respuesta = mapperPersonalizado.dominioADTO(citaMedicaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CitaMedicaResponseDTO>> obtenerCitasMedicas() {
        List<CitaMedica> citasMedicas = citaMedicaService.buscarCitasMedicas();
        List<CitaMedicaResponseDTO> citasMedicasRespuesta = citasMedicas
                .stream()
                .map(mapperPersonalizado::dominioADTO)
                .toList();
        return ResponseEntity.ok(citasMedicasRespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedicaResponseDTO> obtenerCitaMedicaPorId(@PathVariable("id") Long citaMedicaId) {
        Optional<CitaMedica> citaMedicaEncontrada = citaMedicaService.buscarCitaMedicaPorId(citaMedicaId);
        CitaMedicaResponseDTO respuesta = mapperPersonalizado.dominioADTO(citaMedicaEncontrada.orElse(null));
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCitaMedica(@PathVariable("id") Long citaMedicaId) {
        Optional<CitaMedica> citaMedicaExistente = citaMedicaService.buscarCitaMedicaPorId(citaMedicaId);

        if (citaMedicaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        citaMedicaService.eliminarCitaMedica(citaMedicaId);
        return ResponseEntity.noContent().build();
    }
}