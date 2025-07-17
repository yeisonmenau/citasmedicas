package com.yeisonmenau.citasmedicas.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long pacienteId;
    @Column(name = "paciente_nombre")
    private String pacienteNombre;
    @Column(name = "paciente_fecha_nacimiento")
    private LocalDate pacienteFechaNacimiento;
}
