package me.vitorcsouza.jornada_milhas_api.domain.service.conversor;

import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;
import org.springframework.stereotype.Component;

@Component
public class convertDestino {
    public Destino toModel(destinoDtoReq dto){
        return new Destino(dto);
    }

    public destinoDtoRes toDto(Destino destino){
        return new destinoDtoRes(destino);
    }
}
