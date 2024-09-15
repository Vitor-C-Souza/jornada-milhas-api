package me.vitorcsouza.jornada_milhas_api.domain.service.impl;

import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoResWParamGet;
import me.vitorcsouza.jornada_milhas_api.domain.service.conversor.convertDestino;
import me.vitorcsouza.jornada_milhas_api.domain.service.destinoService;
import me.vitorcsouza.jornada_milhas_api.infra.exception.destinoNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.vitorcsouza.jornada_milhas_api.domain.repository.destinoRepository;
import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public destinoDtoResWParamGet findById(Long id) {
        Optional<Destino> optionalDestino = repository.findById(id);
        if (optionalDestino.isPresent()) {
            return convert.toDtoGetDetails(optionalDestino.get());
        } else {
            throw new destinoNotFound("Nenhum destino foi encontrado");
        }

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
        if(destino.isEmpty()){
            throw new destinoNotFound("Nenhum destino foi encontrado");
        }
        return destino.map(destinoDtoRes::new);
    }
}
