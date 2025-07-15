package com.yeisonmenau.citasmedicas.domain.model;

import java.time.LocalDate;

public class Paciente {
    private final Long pacienteId;
    private final String pacienteNombre;
    private final LocalDate pacienteFechaNacimiento;

    public Paciente(Long pacienteId, String pacienteNombre, LocalDate pacienteFechaNacimiento) {
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.pacienteFechaNacimiento = pacienteFechaNacimiento;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public LocalDate getPacienteFechaNacimiento() {
        return pacienteFechaNacimiento;
    }
}
