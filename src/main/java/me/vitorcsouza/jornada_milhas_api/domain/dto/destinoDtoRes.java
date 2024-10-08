package me.vitorcsouza.jornada_milhas_api.domain.dto;

import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;

public record destinoDtoRes(
        Long id,
        String foto1,
        String foto2,
        String nome,
        String meta,
        String textoDescritivo

) {

    public destinoDtoRes(Destino destino){
        this(
                destino.getId(),
                destino.getFoto1(),
                destino.getFoto2(),
                destino.getNome(),
                destino.getMeta(),
                destino.getTextoDescritivo()
        );
    }
}
