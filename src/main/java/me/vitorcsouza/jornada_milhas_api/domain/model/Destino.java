package me.vitorcsouza.jornada_milhas_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;

import java.util.Optional;

@Entity
@Table(name = "destino_tb")
@Getter
@NoArgsConstructor
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto1;
    private String foto2;
    private String nome;
    private String meta;
    private String textoDescritivo;

    public Destino(destinoDtoReq dto) {
        createOrUpdate(dto);
    }

    public void createOrUpdate(destinoDtoReq dto) {
        this.foto1 = dto.foto1();
        this.nome = dto.nome();
        this.foto2 = dto.foto2();
        this.meta = dto.meta();
        this.textoDescritivo = Optional.ofNullable(dto.textoDescritivo()).orElse("");
    }
}
