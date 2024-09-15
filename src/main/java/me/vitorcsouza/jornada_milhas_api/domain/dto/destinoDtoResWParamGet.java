package me.vitorcsouza.jornada_milhas_api.domain.dto;

import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;

public record destinoDtoResWParamGet(
        String foto1,
        String foto2,
        String nome,
        String meta,
        String textoDescritivo
) {

    public destinoDtoResWParamGet(Destino destino){
        this(
                destino.getFoto1(),
                destino.getFoto2(),
                destino.getNome(),
                destino.getMeta(),
                destino.getTextoDescritivo()
        );
    }
}
