package com.yeisonmenau.citasmedicas.infrastructure.persistence.entity;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.model.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "cita_medica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaMedicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citamedica_id")
    private Long citaMedicaId;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoEntity medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;
    private String motivo;
    @Column(name = "citamedica_fecha")
    private LocalDate citaMedicaFecha;
    @Column(name = "citamedica_hora")
    private LocalTime citaMedicaHora;
}
