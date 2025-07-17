package com.yeisonmenau.citasmedicas.infrastructure.controller;

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
import com.yeisonmenau.citasmedicas.infrastructure.mapper.CitaMedicaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/citas-medicas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class CitaMedicaController {

    private final CitaMedicaMapper mapper;
    private final BuscarCitasMedicasUseCase buscarCitasMedicasUseCase;
    private final GuardarCitaMedicaUseCase guardarCitaMedicaUseCase;
    private final BuscarCitaMedicaPorIdUseCase buscarCitaMedicaPorIdUseCase;
    private final EliminarCitaMedicaUseCase eliminarCitaMedicaUseCase;
    private final BuscarMedicoPorIdUseCase buscarMedicoPorIdUseCase;
    private final BuscarPacientePorIdUseCase buscarPacientePorIdUseCase;

    @Autowired
    public CitaMedicaController(CitaMedicaMapper mapper,
                                BuscarCitasMedicasUseCase buscarCitasMedicasUseCase,
                                GuardarCitaMedicaUseCase guardarCitaMedicaUseCase,
                                BuscarCitaMedicaPorIdUseCase buscarCitaMedicaPorIdUseCase,
                                EliminarCitaMedicaUseCase eliminarCitaMedicaUseCase,
                                BuscarMedicoPorIdUseCase buscarMedicoPorIdUseCase,
                                BuscarPacientePorIdUseCase buscarPacientePorIdUseCase) {
        this.mapper = mapper;
        this.buscarCitasMedicasUseCase = buscarCitasMedicasUseCase;
        this.guardarCitaMedicaUseCase = guardarCitaMedicaUseCase;
        this.buscarCitaMedicaPorIdUseCase = buscarCitaMedicaPorIdUseCase;
        this.eliminarCitaMedicaUseCase = eliminarCitaMedicaUseCase;
        this.buscarMedicoPorIdUseCase = buscarMedicoPorIdUseCase;
        this.buscarPacientePorIdUseCase = buscarPacientePorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<CitaMedicaResponseDTO> crearCitaMedica(@RequestBody CitaMedicaRequestDTO citaMedicaRequestDTO) {
        // Verificar que el médico existe
        Optional<Medico> medico = buscarMedicoPorIdUseCase.buscarMedicoPorId(citaMedicaRequestDTO.getMedicoId());
        if (medico.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar que el paciente existe
        Optional<Paciente> paciente = buscarPacientePorIdUseCase.buscarPacientePorId(citaMedicaRequestDTO.getPacienteId());
        if (paciente.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Medico medicoDominio = medico.get();
        Paciente pacienteDominio = paciente.get();

        // Crear la cita médica
        CitaMedica citaMedica = new CitaMedica(null,medicoDominio,pacienteDominio, citaMedicaRequestDTO.getMotivo(),citaMedicaRequestDTO.getCitaMedicaFecha(),citaMedicaRequestDTO.getCitaMedicaHora());

        CitaMedica citaMedicaGuardada = guardarCitaMedicaUseCase.guardarCitaMedica(citaMedica);
        CitaMedicaResponseDTO citaMedicaRespuesta = mapper.dominioADTO(citaMedicaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaMedicaRespuesta);
    }

    @GetMapping
    public ResponseEntity<List<CitaMedicaResponseDTO>> obtenerCitasMedicas() {
        List<CitaMedica> citasMedicas = buscarCitasMedicasUseCase.buscarCitasMedicas();
        List<CitaMedicaResponseDTO> citasMedicasRespuesta = mapper.dominioADTOs(citasMedicas);
        return ResponseEntity.ok(citasMedicasRespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedicaResponseDTO> obtenerCitaMedicaPorId(@PathVariable("id") Long citaMedicaId) {
        Optional<CitaMedica> citaMedicaEncontrada = buscarCitaMedicaPorIdUseCase.buscarCitaMedicaPorId(citaMedicaId);
        return citaMedicaEncontrada
                .map(citaMedica -> ResponseEntity.ok(mapper.dominioADTO(citaMedica)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedicaResponseDTO> actualizarCitaMedica(@PathVariable("id") Long citaMedicaId,
                                                                      @RequestBody CitaMedicaRequestDTO citaMedicaRequestDTO) {
        Optional<CitaMedica> citaMedicaExistente = buscarCitaMedicaPorIdUseCase.buscarCitaMedicaPorId(citaMedicaId);

        if (citaMedicaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar que el médico existe
        Optional<Medico> medico = buscarMedicoPorIdUseCase.buscarMedicoPorId(citaMedicaRequestDTO.getMedicoId());
        if (medico.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar que el paciente existe
        Optional<Paciente> paciente = buscarPacientePorIdUseCase.buscarPacientePorId(citaMedicaRequestDTO.getPacienteId());
        if (paciente.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Actualizar la cita médica
        CitaMedica citaMedicaActualizada = new CitaMedica(
                citaMedicaId,
                medico.get(),
                paciente.get(),
                citaMedicaRequestDTO.getMotivo(),
                citaMedicaRequestDTO.getCitaMedicaFecha(),
                citaMedicaRequestDTO.getCitaMedicaHora()
        );

        CitaMedica citaMedicaGuardada = guardarCitaMedicaUseCase.guardarCitaMedica(citaMedicaActualizada);
        CitaMedicaResponseDTO citaMedicaRespuesta = mapper.dominioADTO(citaMedicaGuardada);
        return ResponseEntity.ok(citaMedicaRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCitaMedica(@PathVariable("id") Long citaMedicaId) {
        Optional<CitaMedica> citaMedicaExistente = buscarCitaMedicaPorIdUseCase.buscarCitaMedicaPorId(citaMedicaId);

        if (citaMedicaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eliminarCitaMedicaUseCase.eliminarCitaMedica(citaMedicaId);
        return ResponseEntity.noContent().build();
    }
}