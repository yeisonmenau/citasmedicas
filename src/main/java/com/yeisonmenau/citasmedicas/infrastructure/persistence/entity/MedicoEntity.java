package com.yeisonmenau.citasmedicas.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "medico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medico_id")
    private Long medicoId;
    @Column(name = "medico_nombre")
    private String medicoNombre;
    @Column(name = "medico_especialidad")
    private String medicoEspecialidad;
    @Column(name = "medico_fecha_nacimiento")
    private LocalDate medicoFechaNacimiento;
}
