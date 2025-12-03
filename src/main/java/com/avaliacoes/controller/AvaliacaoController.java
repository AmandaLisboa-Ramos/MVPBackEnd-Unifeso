package com.avaliacoes.controller;

import com.avaliacoes.dto.AvaliacaoRequestDTO;
import com.avaliacoes.dto.AvaliacaoResponseDTO;
import com.avaliacoes.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    
    @Autowired
    private AvaliacaoService service;
    
    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@Valid @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO avaliacao = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
    
    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarTodas() {
        List<AvaliacaoResponseDTO> avaliacoes = service.listarTodas();
        return ResponseEntity.ok(avaliacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        AvaliacaoResponseDTO avaliacao = service.buscarPorId(id);
        return ResponseEntity.ok(avaliacao);
    }
    
    @GetMapping("/ponto/{pontoId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorPontoTuristico(@PathVariable Long pontoId) {
        List<AvaliacaoResponseDTO> avaliacoes = service.listarPorPontoTuristico(pontoId);
        return ResponseEntity.ok(avaliacoes);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO avaliacao = service.atualizar(id, dto);
        return ResponseEntity.ok(avaliacao);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
