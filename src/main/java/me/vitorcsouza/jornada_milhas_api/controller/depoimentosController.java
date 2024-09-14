package me.vitorcsouza.jornada_milhas_api.controller;

import jakarta.validation.Valid;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.service.impl.depoimentosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
public class depoimentosController {
    @Autowired
    private depoimentosServiceImpl service;

    @PostMapping("/depoimentos")
    public ResponseEntity<depoimentosDtoRes> create(@RequestBody @Valid depoimentosDtoReq dto, UriComponentsBuilder uri) {
        depoimentosDtoRes dtoRes = service.create(dto);
        URI endereco = uri.path("depoimentos/{id}").buildAndExpand(dtoRes.id()).toUri();
        return ResponseEntity.created(endereco).body(dtoRes);
    }

    @GetMapping("/depoimentos/{id}")
    public ResponseEntity<depoimentosDtoRes> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/depoimentos")
    public ResponseEntity<Page<depoimentosDtoRes>> findAll(@PageableDefault Pageable pag) {
        return ResponseEntity.ok(service.findAll(pag));
    }

    @PutMapping("/depoimentos/{id}")
    public ResponseEntity<depoimentosDtoRes> update(@PathVariable Long id, @RequestBody @Valid depoimentosDtoReq dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/depoimentos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity<Page<depoimentosDtoRes>> findRandom(@PageableDefault(size = 3) Pageable pag) {
        return ResponseEntity.ok(service.findRandom(pag));
    }
}
