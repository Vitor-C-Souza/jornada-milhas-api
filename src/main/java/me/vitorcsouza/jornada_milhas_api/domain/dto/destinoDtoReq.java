package me.vitorcsouza.jornada_milhas_api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record destinoDtoReq(
        @NotNull @NotBlank String foto,
        @NotNull @NotBlank String nome,
        @NotNull double preco) {
}
