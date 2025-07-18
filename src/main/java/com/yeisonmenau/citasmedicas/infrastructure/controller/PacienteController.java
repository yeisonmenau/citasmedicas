package com.yeisonmenau.citasmedicas.infrastructure.controller;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientePorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientesUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarPacienteUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarPacienteUseCase;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.PacienteMapper;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pacientes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class PacienteController {

    private final PacienteMapper mapper;
    private final BuscarPacientesUseCase buscarPacientesUseCase;
    private final GuardarPacienteUseCase guardarPacienteUseCase;
    private final BuscarPacientePorIdUseCase buscarPacientePorIdUseCase;
    private final EliminarPacienteUseCase eliminarPacienteUseCase;

    @Autowired
    public PacienteController(PacienteMapper mapper,
                              BuscarPacientesUseCase buscarPacientesUseCase,
                              GuardarPacienteUseCase guardarPacienteUseCase,
                              BuscarPacientePorIdUseCase buscarPacientePorIdUseCase,
                              EliminarPacienteUseCase eliminarPacienteUseCase) {
        this.mapper = mapper;
        this.buscarPacientesUseCase = buscarPacientesUseCase;
        this.guardarPacienteUseCase = guardarPacienteUseCase;
        this.buscarPacientePorIdUseCase = buscarPacientePorIdUseCase;
        this.eliminarPacienteUseCase = eliminarPacienteUseCase;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.guardarPacienteUseCase.guardarPaciente(pacienteRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> obtenerPacientes() {
        List<PacienteResponseDTO> pacientes = buscarPacientesUseCase.buscarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable("id") Long pacienteId) {
        Optional<PacienteResponseDTO> respuesta = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);
        if (respuesta.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Paciente con ID " + pacienteId + " no encontrado.");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPaciente(
                                                @PathVariable("id") Long pacienteId,
                                                @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Optional<PacienteResponseDTO> pacienteExistente = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);

        if (pacienteExistente.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Paciente con ID " + pacienteId + " no encontrado.");
        }

        // Crear nuevo paciente con el ID existente
        PacienteEntity pacienteActualizado = new PacienteEntity(
                pacienteId,
                pacienteRequestDTO.getPacienteNombre(),
                pacienteRequestDTO.getPacienteFechaNacimiento()
        );

        PacienteResponseDTO pacienteGuardado = guardarPacienteUseCase.guardarPaciente(pacienteActualizado);
        return ResponseEntity.ok(pacienteGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("id") Long pacienteId) {
        Optional<PacienteResponseDTO> pacienteExistente = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);
        if (pacienteExistente.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Paciente con ID " + pacienteId + " no encontrado.");
        }
        eliminarPacienteUseCase.eliminarPaciente(pacienteId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Paciente con ID " + pacienteId + " eliminado exitosamente.");
    }
}