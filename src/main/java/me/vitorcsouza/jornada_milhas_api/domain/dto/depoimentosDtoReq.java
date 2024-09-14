package me.vitorcsouza.jornada_milhas_api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record depoimentosDtoReq (
        @NotNull @NotBlank String texto,
        @NotNull @NotBlank String autor,
        @NotNull @NotBlank String avatar
) {
}
