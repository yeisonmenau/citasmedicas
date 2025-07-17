package com.yeisonmenau.citasmedicas.infrastructure.persistence.adapter;

import com.yeisonmenau.citasmedicas.domain.model.CitaMedica;
import com.yeisonmenau.citasmedicas.domain.repository.CitaMedicoRepository;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.CitaMedicaMapper;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.CitaMedicaEntity;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.repository.JpaCitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CitaMedicaAdapter implements CitaMedicoRepository {

    private final JpaCitaMedicaRepository jpaCitaMedicaRepository;
    private final CitaMedicaMapper mapper;

    @Autowired
    public CitaMedicaAdapter(JpaCitaMedicaRepository jpaCitaMedicaRepository, CitaMedicaMapper mapper) {
        this.jpaCitaMedicaRepository = jpaCitaMedicaRepository;
        this.mapper = mapper;
    }

    @Override
    public CitaMedica guardarCitaMedica(CitaMedica citaMedica) {
        CitaMedicaEntity entidad = mapper.dominioAEntidad(citaMedica);
        CitaMedicaEntity guardado = jpaCitaMedicaRepository.save(entidad);
        return mapper.entidadADominio(guardado);
    }

    @Override
    public List<CitaMedica> buscarCitasMedicas() {
        return mapper.entidadesADominios(jpaCitaMedicaRepository.findAll());
    }

    @Override
    public Optional<CitaMedica> buscarCitaMedicaPorId(Long citaMedicaId) {
        return jpaCitaMedicaRepository.findById(citaMedicaId)
                .map(mapper::entidadADominio);
    }

    @Override
    public void eliminarCitaMedica(Long citaMedicaId) {
        jpaCitaMedicaRepository.deleteById(citaMedicaId);
    }
}