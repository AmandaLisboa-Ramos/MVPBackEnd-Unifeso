package com.mvp.api_turismo.controller;

import com.mvp.api_turismo.dto.PontoTuristicoDto;
import com.mvp.api_turismo.service.PontoTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos")
public class PontoTuristicoController {

    @Autowired
    private PontoTuristicoService service;

    @GetMapping
    public ResponseEntity<List<PontoTuristicoDto>> listarPontos() {
        return ResponseEntity.ok(service.listarPontos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoTuristicoDto> buscarPontoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPontoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarPonto(@RequestBody PontoTuristicoDto dto) {
        service.cadastrarPonto(dto);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPonto(@PathVariable Long id, @RequestBody PontoTuristicoDto dto) {
        service.atualizarPonto(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPonto(@PathVariable Long id) {
        service.deletarPonto(id);
        return ResponseEntity.ok().build();
    }
}
