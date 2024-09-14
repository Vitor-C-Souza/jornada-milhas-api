package me.vitorcsouza.jornada_milhas_api.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.service.conversor.convertDestino;
import me.vitorcsouza.jornada_milhas_api.domain.service.destinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.vitorcsouza.jornada_milhas_api.domain.repository.destinoRepository;
import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class destinoServiceImpl implements destinoService {
    @Autowired
    private destinoRepository repository;

    @Autowired
    private convertDestino convert;

    @Override
    public destinoDtoRes create(destinoDtoReq dto) {
        Destino destino = convert.toModel(dto);
        repository.save(destino);
        return convert.toDto(destino);
    }

    @Override
    public destinoDtoRes findById(Long id) {
        Optional<Destino> optionalDestino = repository.findById(id);
        if (optionalDestino.isPresent()) {
            return convert.toDto(optionalDestino.get());
        }
        throw new NoSuchElementException("Destino with this id not found.");
    }

    @Override
    public Page<destinoDtoRes> findAll(Pageable pag) {
        Page<Destino> destinos = repository.findAll(pag);
        return destinos.map(destinoDtoRes::new);
    }

    @Override
    @Transactional
    public destinoDtoRes update(Long id, destinoDtoReq dto) {
        Destino referenceById = repository.getReferenceById(id);
        referenceById.createOrUpdate(dto);
        repository.save(referenceById);
        return convert.toDto(referenceById);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<destinoDtoRes> findBySearch(String nome, Pageable pageable) {
        Page<Destino> destino = repository.findBySearch(nome, pageable);
        return destino.map(destinoDtoRes::new);
    }
}
