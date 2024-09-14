package me.vitorcsouza.jornada_milhas_api.domain.dto;

import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;

import java.math.BigDecimal;

public record destinoDtoRes(
        Long id,
        String foto,
        String nome,
        BigDecimal preco) {

    public destinoDtoRes(Destino destino){
        this(
                destino.getId(),
                destino.getFoto(),
                destino.getNome(),
                destino.getPreco()
        );
    }
}
