package me.vitorcsouza.jornada_milhas_api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record destinoDtoReq(
        @NotNull @NotBlank String foto1,
        @NotNull @NotBlank String foto2,
        @NotNull @NotBlank String nome,
        @NotNull @NotBlank @Size(max = 160) String meta,
        String textoDescritivo) {
}
