package com.avaliacoes.service;

import com.avaliacoes.dto.AvaliacaoRequestDTO;
import com.avaliacoes.dto.AvaliacaoResponseDTO;
import com.avaliacoes.entity.Avaliacao;
import com.avaliacoes.exception.InvalidDataException;
import com.avaliacoes.exception.ResourceNotFoundException;
import com.avaliacoes.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {
    
    @Autowired
    private AvaliacaoRepository repository;
    
    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        validarNota(dto.getNota());
        
        Avaliacao avaliacao = converterDtoParaEntity(dto);
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        
        Avaliacao avaliacaoSalva = repository.save(avaliacao);
        
        return converterEntityParaDto(avaliacaoSalva);
    }
    
    public List<AvaliacaoResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::converterEntityParaDto)
                .collect(Collectors.toList());
    }
    
    public AvaliacaoResponseDTO buscarPorId(Long id) {
        Avaliacao avaliacao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Avaliação com ID " + id + " não encontrada"));
        
        return converterEntityParaDto(avaliacao);
    }
    
    public List<AvaliacaoResponseDTO> listarPorPontoTuristico(Long pontoTuristicoId) {
        return repository.findByPontoTuristicoId(pontoTuristicoId)
                .stream()
                .map(this::converterEntityParaDto)
                .collect(Collectors.toList());
    }
    
    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto) {
        validarNota(dto.getNota());
        
        Avaliacao avaliacaoExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Avaliação com ID " + id + " não encontrada"));
        
        avaliacaoExistente.setPontoTuristicoId(dto.getPontoTuristicoId());
        avaliacaoExistente.setUsuarioId(dto.getUsuarioId());
        avaliacaoExistente.setNota(dto.getNota());
        avaliacaoExistente.setComentario(dto.getComentario());
        
        Avaliacao avaliacaoAtualizada = repository.save(avaliacaoExistente);
        
        return converterEntityParaDto(avaliacaoAtualizada);
    }
    
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Avaliação com ID " + id + " não encontrada");
        }
        
        repository.deleteById(id);
    }
    
    private void validarNota(Integer nota) {
        if (nota == null || nota < 1 || nota > 5) {
            throw new InvalidDataException(
                    "A nota deve estar entre 1 e 5. Valor informado: " + nota);
        }
    }
    
    private Avaliacao converterDtoParaEntity(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPontoTuristicoId(dto.getPontoTuristicoId());
        avaliacao.setUsuarioId(dto.getUsuarioId());
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        return avaliacao;
    }
    
    private AvaliacaoResponseDTO converterEntityParaDto(Avaliacao entity) {
        return new AvaliacaoResponseDTO(
                entity.getId(),
                entity.getPontoTuristicoId(),
                entity.getUsuarioId(),
                entity.getNota(),
                entity.getComentario(),
                entity.getDataAvaliacao()
        );
    }
}
