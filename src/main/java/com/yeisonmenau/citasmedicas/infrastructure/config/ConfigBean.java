package com.yeisonmenau.citasmedicas.infrastructure.config;

import com.yeisonmenau.citasmedicas.application.service.CitaMedicaService;
import com.yeisonmenau.citasmedicas.application.service.MedicoService;
import com.yeisonmenau.citasmedicas.application.service.PacienteService;
import com.yeisonmenau.citasmedicas.domain.repository.CitaMedicoRepository;
import com.yeisonmenau.citasmedicas.domain.repository.MedicoRepository;
import com.yeisonmenau.citasmedicas.domain.repository.PacienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public PacienteService pacienteService (PacienteRepository pacienteRepository){
        return new PacienteService(pacienteRepository);
    }
    @Bean
    public MedicoService medicoService (MedicoRepository medicoRepository){
        return new MedicoService(medicoRepository);
    }
    @Bean
    public CitaMedicaService citaMedicaService (CitaMedicoRepository citaMedicoRepository){
        return new CitaMedicaService(citaMedicoRepository);
    }
}
