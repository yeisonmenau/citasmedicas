package com.yeisonmenau.citasmedicas.infrastructure.persistence.adapter;

import com.yeisonmenau.citasmedicas.domain.model.Medico;
import com.yeisonmenau.citasmedicas.domain.repository.MedicoRepository;
import com.yeisonmenau.citasmedicas.infrastructure.mapper.MedicoMapper;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.entity.MedicoEntity;
import com.yeisonmenau.citasmedicas.infrastructure.persistence.repository.JpaMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MedicoAdapter implements MedicoRepository {

    private final JpaMedicoRepository jpaMedicoRepository;
    private final MedicoMapper mapper;

    @Autowired
    public MedicoAdapter(JpaMedicoRepository jpaMedicoRepository, MedicoMapper mapper) {
        this.jpaMedicoRepository = jpaMedicoRepository;
        this.mapper = mapper;
    }

    @Override
    public Medico guardarMedico(Medico medico) {
        MedicoEntity entidad = mapper.dominioAEntidad(medico);
        MedicoEntity guardado = jpaMedicoRepository.save(entidad);
        return mapper.entidadADominio(guardado);
    }

    @Override
    public List<Medico> buscarMedicos() {
        return mapper.entidadesADominios(jpaMedicoRepository.findAll());
    }

    @Override
    public Optional<Medico> buscarMedicoPorId(Long medicoId) {
        return jpaMedicoRepository.findById(medicoId)
                .map(mapper::entidadADominio);
    }

    @Override
    public void eliminarMedico(Long medicoId) {
        jpaMedicoRepository.deleteById(medicoId);
    }
}