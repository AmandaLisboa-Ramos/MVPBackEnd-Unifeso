package com.mvp.api_turismo.service;

import com.mvp.api_turismo.Repository.PontoTuristicoRepository;
import com.mvp.api_turismo.dto.PontoTuristicoDto;
import com.mvp.api_turismo.model.PontoTuristico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoTuristicoService {

    @Autowired
    private PontoTuristicoRepository repository;

    public void cadastrarPonto(PontoTuristicoDto dto) {
        PontoTuristico ponto = PontoTuristico.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .tipo(dto.tipo())
                .localizacao(dto.localizacao())
                .nivelDificuldade(dto.nivelDificuldade())
                .imagemUrl(dto.imagemUrl())
                .build();
        repository.save(ponto);
    }

    public List<PontoTuristicoDto> listarPontos() {
        return repository.findAll().stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    public PontoTuristicoDto buscarPontoPorId(Long id) {
        PontoTuristico ponto = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Ponto turístico não encontrado: " + id));
        return converterParaDto(ponto);
    }

    public void atualizarPonto(Long id, PontoTuristicoDto dto) {
        PontoTuristico ponto = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Ponto turístico não encontrado: " + id));

        ponto.setNome(dto.nome());
        ponto.setDescricao(dto.descricao());
        ponto.setTipo(dto.tipo());
        ponto.setLocalizacao(dto.localizacao());
        ponto.setNivelDificuldade(dto.nivelDificuldade());
        ponto.setImagemUrl(dto.imagemUrl());

        repository.save(ponto);
    }

    @Transactional
    public void deletarPonto(Long id) {
        if (!repository.existsById(id)) {
            throw new NullPointerException("Ponto turístico não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private PontoTuristicoDto converterParaDto(PontoTuristico ponto) {
        return new PontoTuristicoDto(
                ponto.getId(),
                ponto.getNome(),
                ponto.getDescricao(),
                ponto.getTipo(),
                ponto.getLocalizacao(),
                ponto.getNivelDificuldade(),
                ponto.getImagemUrl(),
                ponto.getDataCadastro());
    }
}
