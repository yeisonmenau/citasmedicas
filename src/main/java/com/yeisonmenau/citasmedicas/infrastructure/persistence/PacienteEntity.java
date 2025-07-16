package com.yeisonmenau.citasmedicas.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacienteId;
    private String pacienteNombre;
    private LocalDate pacienteFechaNacimiento;
}
