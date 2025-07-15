package com.yeisonmenau.citasmedicas.domain.model;

import java.time.LocalDate;

public class Medico {
    private final Long medicoId;
    private final String medicoNombre;
    private final String medicoEspecialidad;
    private final LocalDate medicoFechaNacimiento;

    public Medico(Long medicoId, String medicoNombre, String medicoEspecialidad, LocalDate medicoFechaNacimiento) {
        this.medicoId = medicoId;
        this.medicoNombre = medicoNombre;
        this.medicoEspecialidad = medicoEspecialidad;
        this.medicoFechaNacimiento = medicoFechaNacimiento;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public String getMedicoEspecialidad() {
        return medicoEspecialidad;
    }

    public LocalDate getMedicoFechaNacimiento() {
        return medicoFechaNacimiento;
    }
}
