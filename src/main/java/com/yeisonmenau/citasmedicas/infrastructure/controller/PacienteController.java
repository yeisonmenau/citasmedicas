package com.yeisonmenau.citasmedicas.infrastructure.controller;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientePorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarPacientesUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarPacienteUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarPacienteUseCase;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.PacienteRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.PacienteResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.PacienteMapper;
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
        Paciente pacienteDominio = mapper.dtoADominio(pacienteRequestDTO);
        Paciente pacienteGuardado = guardarPacienteUseCase.guardarPaciente(pacienteDominio);
        PacienteResponseDTO pacienteRespuesta = mapper.dominioADTO(pacienteGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRespuesta);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> obtenerPacientes() {
        List<Paciente> pacientes = buscarPacientesUseCase.buscarPacientes();
        List<PacienteResponseDTO> pacientesRespuesta = mapper.dominioADTOs(pacientes);
        return ResponseEntity.ok(pacientesRespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPacientePorId(@PathVariable("id") Long pacienteId) {
        Optional<Paciente> pacienteEncontrado = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);
        return pacienteEncontrado
                .map(paciente -> ResponseEntity.ok(mapper.dominioADTO(paciente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(@PathVariable("id") Long pacienteId,
                                                                  @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Optional<Paciente> pacienteExistente = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);

        if (pacienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Crear nuevo paciente con el ID existente
        Paciente pacienteActualizado = new Paciente(
                pacienteId,
                pacienteRequestDTO.getPacienteNombre(),
                pacienteRequestDTO.getPacienteFechaNacimiento()
        );

        Paciente pacienteGuardado = guardarPacienteUseCase.guardarPaciente(pacienteActualizado);
        PacienteResponseDTO pacienteRespuesta = mapper.dominioADTO(pacienteGuardado);
        return ResponseEntity.ok(pacienteRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable("id") Long pacienteId) {
        Optional<Paciente> pacienteExistente = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);

        if (pacienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eliminarPacienteUseCase.eliminarPaciente(pacienteId);
        return ResponseEntity.noContent().build();
    }
}