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
@RequestMapping(name = "/pacientes")
public class PacienteController {

    private final PacienteMapper mapper;
    private final BuscarPacientesUseCase buscarPacientesUseCase;
    private final GuardarPacienteUseCase guardarPacienteUseCase;
    private final BuscarPacientePorIdUseCase buscarPacientePorIdUseCase;
    private final EliminarPacienteUseCase eliminarPacienteUseCase;

    @Autowired
    public PacienteController(PacienteMapper mapper, BuscarPacientesUseCase buscarPacientesUseCase, GuardarPacienteUseCase guardarPacienteUseCase, BuscarPacientePorIdUseCase buscarPacientePorIdUseCase, EliminarPacienteUseCase eliminarPacienteUseCase) {
        this.mapper = mapper;
        this.buscarPacientesUseCase = buscarPacientesUseCase;
        this.guardarPacienteUseCase = guardarPacienteUseCase;
        this.buscarPacientePorIdUseCase = buscarPacientePorIdUseCase;
        this.eliminarPacienteUseCase = eliminarPacienteUseCase;
    }
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO){
        Paciente pacienteDominio = mapper.dtoADominio(pacienteRequestDTO);
        Paciente pacienteGuardado = guardarPacienteUseCase.guardarPaciente(pacienteDominio);
        PacienteResponseDTO pacienteRespuesta = mapper.dominioADTO(pacienteGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRespuesta);
    }
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> mostrarPacientes (){
        List<Paciente> pacientes = buscarPacientesUseCase.buscarPacientes();
        return ResponseEntity.ok(mapper.dominioADTOs(pacientes));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> mostrarPorId (@PathVariable("id") Long pacienteId){
        Optional<Paciente> pacienteEncontrado = buscarPacientePorIdUseCase.buscarPacientePorId(pacienteId);
        return pacienteEncontrado.map(paciente -> ResponseEntity.ok(mapper.dominioADTO(paciente)))
                .orElse(ResponseEntity.notFound().build());
    }




}
