package me.vitorcsouza.jornada_milhas_api.domain.dto;

import me.vitorcsouza.jornada_milhas_api.domain.model.Depoimento;

public record depoimentosDtoRes(
        Long id,
        String texto,
        String autor,
        String avatar
) {
    public depoimentosDtoRes(Depoimento depoimento){
        this(
                depoimento.getId(),
                depoimento.getTexto(),
                depoimento.getAutor(),
                depoimento.getAvatar()
        );
    }
}
