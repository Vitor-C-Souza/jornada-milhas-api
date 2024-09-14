package me.vitorcsouza.jornada_milhas_api.controller;

import jakarta.validation.Valid;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.service.destinoService;
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
    public ResponseEntity<destinoDtoRes> createDestino(@RequestBody @Valid destinoDtoReq dto, UriComponentsBuilder uri) {
        destinoDtoRes dtoRes = service.create(dto);
        URI endereco = uri.path("/destinos/{id}").buildAndExpand(dtoRes.id()).toUri();
        return ResponseEntity.created(endereco).body(dtoRes);
    }

    @GetMapping
    public ResponseEntity<Page<destinoDtoRes>> findAll(@RequestParam(required = false) String nome, @PageableDefault Pageable pageable) {
        Page<destinoDtoRes> dtoResPage;
        if (nome.isBlank()) {
            dtoResPage = service.findAll(pageable);
        } else {
            dtoResPage = service.findBySearch(nome, pageable);
        }

        return ResponseEntity.ok(dtoResPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<destinoDtoRes> findById(@PathVariable Long id) {
        destinoDtoRes dtoRes = service.findById(id);
        return ResponseEntity.ok(dtoRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<destinoDtoRes> update(@PathVariable Long id, @RequestBody destinoDtoReq dto) {
        destinoDtoRes dtoRes = service.update(id, dto);
        return ResponseEntity.ok(dtoRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
