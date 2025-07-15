package com.yeisonmenau.citasmedicas.domain.model;

import java.time.LocalDate;

public class CitaMedica {
    private final Long citaMedicaId;
    private final Medico medico;
    private final Paciente paciente;
    private final String motivo;
    private final LocalDate citaMedicaFecha;

    public CitaMedica(Long citaMedicaId, Medico medico, Paciente paciente, String motivo, LocalDate citaMedicaFecha) {
        this.citaMedicaId = citaMedicaId;
        this.medico = medico;
        this.paciente = paciente;
        this.motivo = motivo;
        this.citaMedicaFecha = citaMedicaFecha;
    }

    public Long getCitaMedicaId() {
        return citaMedicaId;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDate getCitaMedicaFecha() {
        return citaMedicaFecha;
    }
}
