package com.yeisonmenau.citasmedicas.infrastructure.controller;

import com.yeisonmenau.citasmedicas.application.usecase.BuscarMedicoPorIdUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.BuscarMedicosUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.EliminarMedicoUseCase;
import com.yeisonmenau.citasmedicas.application.usecase.GuardarMedicoUseCase;
import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.infrastructure.dto.request.MedicoRequestDTO;
import com.yeisonmenau.citasmedicas.infrastructure.dto.response.MedicoResponseDTO;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.MedicoMapper;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.MedicoMapperPersonalizado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/medicos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoMapper mapper;
    private final BuscarMedicosUseCase buscarMedicosUseCase;
    private final GuardarMedicoUseCase guardarMedicoUseCase;
    private final BuscarMedicoPorIdUseCase buscarMedicoPorIdUseCase;
    private final EliminarMedicoUseCase eliminarMedicoUseCase;
    private final MedicoMapperPersonalizado medicoMapperPersonalizado;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> crearMedico(@RequestBody MedicoRequestDTO medicoRequestDTO) {
        Medico medicoDominio = mapper.dtoADominio(medicoRequestDTO);
        Medico medicoGuardado = guardarMedicoUseCase.guardarMedico(medicoDominio);
        MedicoResponseDTO medicoRespuesta = medicoMapperPersonalizado.dominioADTO(medicoGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRespuesta);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> obtenerMedicos() {
        List<Medico> medicos = buscarMedicosUseCase.buscarMedicos();
        List<MedicoResponseDTO> medicosRespuesta = medicos.stream().map(medicoMapperPersonalizado::dominioADTO).toList();
        return ResponseEntity.ok(medicosRespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> obtenerMedicoPorId(@PathVariable("id") Long medicoId) {
        Optional<Medico> medicoEncontrado = buscarMedicoPorIdUseCase.buscarMedicoPorId(medicoId);
        MedicoResponseDTO respuesta = medicoMapperPersonalizado.dominioADTO(medicoEncontrado.orElse(null));
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> actualizarMedico(@PathVariable("id") Long medicoId,
                                                              @RequestBody MedicoRequestDTO medicoRequestDTO) {
        Optional<Medico> medicoExistente = buscarMedicoPorIdUseCase.buscarMedicoPorId(medicoId);

        if (medicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Crear nuevo médico con el ID existente
        Medico medicoActualizado = new Medico(medicoId,
                medicoRequestDTO.getMedicoNombre(),
                medicoRequestDTO.getMedicoEspecialidad(),
                medicoRequestDTO.getMedicoFechaNacimiento());

        Medico medicoGuardado = guardarMedicoUseCase.guardarMedico(medicoActualizado);
        MedicoResponseDTO medicoRespuesta = medicoMapperPersonalizado.dominioADTO(medicoGuardado);
        return ResponseEntity.ok(medicoRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable("id") Long medicoId) {
        Optional<Medico> medicoExistente = buscarMedicoPorIdUseCase.buscarMedicoPorId(medicoId);

        if (medicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eliminarMedicoUseCase.eliminarMedico(medicoId);
        return ResponseEntity.noContent().build();
    }
}