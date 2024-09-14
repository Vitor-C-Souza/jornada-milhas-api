package me.vitorcsouza.jornada_milhas_api.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.repository.depoimentosRepository;
import me.vitorcsouza.jornada_milhas_api.domain.service.conversor.convertDepoimento;
import me.vitorcsouza.jornada_milhas_api.domain.service.depoimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.vitorcsouza.jornada_milhas_api.domain.model.Depoimento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class depoimentosServiceImpl implements depoimentosService {
    @Autowired
    private convertDepoimento convert;
    @Autowired
    private depoimentosRepository repository;

    @Override
    @Transactional
    public depoimentosDtoRes create(depoimentosDtoReq dto) {
        Depoimento depoimento = convert.toModel(dto);
        repository.save(depoimento);
        return convert.toDto(depoimento);
    }

    @Override
    public depoimentosDtoRes findById(Long id) {
        Optional<Depoimento> depoimentoOptional = repository.findById(id);
        if (depoimentoOptional.isPresent()) {
            return convert.toDto(depoimentoOptional.get());
        }
        throw new EntityNotFoundException("Depoimento with this id not found.");
    }

    @Override
    @Transactional
    public Page<depoimentosDtoRes> findAll(Pageable pag) {
        Page<Depoimento> depoimentosPage = repository.findAll(pag);
        return depoimentosPage.map(depoimentosDtoRes::new);
    }

    @Override
    @Transactional
    public depoimentosDtoRes update(Long id, depoimentosDtoReq dto) {
        Depoimento referenceById = repository.getReferenceById(id);
        referenceById.createOrUpdate(dto);
        repository.save(referenceById);
        return convert.toDto(referenceById);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<depoimentosDtoRes> findRandom(Pageable pag) {
        Page<Depoimento> depoimentosPage = repository.findRandom(pag);
        return depoimentosPage.map(depoimentosDtoRes::new);
    }
}
