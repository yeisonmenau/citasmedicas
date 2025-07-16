package com.yeisonmenau.citasmedicas.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "medico")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicoId;
    private String medicoNombre;
    private String medicoEspecialidad;
    private LocalDate medicoFechaNacimiento;
}
