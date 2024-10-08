package me.vitorcsouza.jornada_milhas_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;

@Entity
@Table(name = "depoimentos_tb")
@Getter
@NoArgsConstructor
public class Depoimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private String autor;
    private String avatar;

    public Depoimento(depoimentosDtoReq dto) {
        createOrUpdate(dto);
    }

    public void createOrUpdate(depoimentosDtoReq dto) {
        this.texto = dto.texto();
        this.autor = dto.autor();
        this.avatar = dto.avatar();
    }
}
