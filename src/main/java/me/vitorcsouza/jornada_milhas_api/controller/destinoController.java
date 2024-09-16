package me.vitorcsouza.jornada_milhas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoResWParamGet;
import me.vitorcsouza.jornada_milhas_api.domain.service.destinoService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/destinos")
public class destinoController {
    @Autowired
    private destinoService service;

    @PostMapping
    @Operation(summary = "Cria um novo destino", tags = {"destinos"})
    public ResponseEntity<destinoDtoRes> createDestino(@RequestBody @Valid destinoDtoReq dto, UriComponentsBuilder uri) {
        destinoDtoRes dtoRes = service.create(dto);
        URI endereco = uri.path("/destinos/{id}").buildAndExpand(dtoRes.id()).toUri();
        return ResponseEntity.created(endereco).body(dtoRes);
    }

    @GetMapping
    @Operation(summary = "Lista todos os destinos ou aqueles que enviar como parametro na requisição como busca por destinos especificos", tags = {"destinos"})
    public ResponseEntity<Page<destinoDtoRes>> findAll(@RequestParam(required = false) String nome, @PageableDefault @ParameterObject Pageable pageable) {
        Page<destinoDtoRes> dtoResPage;
        if (nome == null) {
            dtoResPage = service.findAll(pageable);
        } else {
            dtoResPage = service.findBySearch(nome, pageable);
        }
        return ResponseEntity.ok(dtoResPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura um destino especifico pelo seu id", tags = {"destinos"})
    public ResponseEntity<destinoDtoResWParamGet> findById(@PathVariable Long id) {
        destinoDtoResWParamGet dtoRes = service.findById(id);
        return ResponseEntity.ok(dtoRes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um destino", tags = {"destinos"})
    public ResponseEntity<destinoDtoRes> update(@PathVariable Long id, @RequestBody destinoDtoReq dto) {
        destinoDtoRes dtoRes = service.update(id, dto);
        return ResponseEntity.ok(dtoRes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga um destino salvo pelo seu id", tags = {"destinos"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
