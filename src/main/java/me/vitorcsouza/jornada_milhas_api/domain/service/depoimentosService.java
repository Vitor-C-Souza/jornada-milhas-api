package me.vitorcsouza.jornada_milhas_api.domain.service;

import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface depoimentosService {
    depoimentosDtoRes create(depoimentosDtoReq dto);

    depoimentosDtoRes findById(Long id);

    Page<depoimentosDtoRes> findAll(Pageable pag);

    depoimentosDtoRes update(Long id, depoimentosDtoReq dto);

    void delete(Long id);

    Page<depoimentosDtoRes> findRandom(Pageable pag);
}
