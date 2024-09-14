package me.vitorcsouza.jornada_milhas_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;

import java.math.BigDecimal;

@Entity
@Table(name = "destino_tb")
@Getter
@NoArgsConstructor
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto;
    private String nome;
    private BigDecimal preco;

    public Destino(destinoDtoReq dto) {
        createOrUpdate(dto);
    }

    public void createOrUpdate(destinoDtoReq dto){
        this.foto = dto.foto();
        this.nome = dto.nome();
        this.preco = BigDecimal.valueOf(dto.preco());
    }
}
