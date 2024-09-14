package me.vitorcsouza.jornada_milhas_api.domain.service.conversor;

import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.model.Depoimentos;
import org.springframework.stereotype.Component;

@Component
public class convertDepoimento {
    public depoimentosDtoRes toDto(Depoimentos depoimento) {
        return new depoimentosDtoRes(depoimento);
    }

    public Depoimentos toModel(depoimentosDtoReq dto) {
        return new Depoimentos(dto);
    }
}
