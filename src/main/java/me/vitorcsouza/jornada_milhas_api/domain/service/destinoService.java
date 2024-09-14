package me.vitorcsouza.jornada_milhas_api.domain.service;

import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface destinoService {
    destinoDtoRes create(destinoDtoReq dto);

    destinoDtoRes findById(Long id);

    Page<destinoDtoRes> findAll(Pageable pag);

    destinoDtoRes update(Long id, destinoDtoReq dto);

    void delete(Long id);

    Page<destinoDtoRes> findBySearch(String search, Pageable pageable);
}
