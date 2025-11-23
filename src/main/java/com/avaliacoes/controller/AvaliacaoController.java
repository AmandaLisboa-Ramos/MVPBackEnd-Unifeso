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

/**
 * CONTROLLER REST - CAMADA DE APRESENTAÇÃO
 * 
 * Esta classe define os endpoints da API REST para gerenciar avaliações.
 * É a porta de entrada da aplicação - recebe requisições HTTP e retorna respostas.
 * 
 * @RestController marca esta classe como um controller REST
 * @RequestMapping define o caminho base: /avaliacoes
 * 
 * Responsabilidades:
 * - Receber requisições HTTP
 * - Validar entrada (Bean Validation)
 * - Chamar o Service para executar a lógica
 * - Retornar resposta HTTP adequada
 */
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    
    /**
     * Injeção de dependência do Service
     * @Autowired faz o Spring fornecer automaticamente uma instância
     */
    @Autowired
    private AvaliacaoService service;
    
    /**
     * ENDPOINT: CRIAR AVALIAÇÃO
     * 
     * POST /avaliacoes
     * 
     * Recebe um JSON no body da requisição com:
     * - pontoTuristicoId
     * - usuarioId
     * - nota
     * - comentario (opcional)
     * 
     * @Valid ativa as validações do Bean Validation no DTO
     * @RequestBody indica que os dados vêm no corpo da requisição
     * 
     * Retorna HTTP 201 (Created) com a avaliação criada
     * 
     * @param dto Dados da nova avaliação
     * @return Avaliação criada com ID e data gerados
     */
    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@Valid @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO avaliacao = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
    
    /**
     * ENDPOINT: LISTAR TODAS AS AVALIAÇÕES
     * 
     * GET /avaliacoes
     * 
     * Retorna HTTP 200 (OK) com lista de todas as avaliações
     * 
     * @return Lista de todas as avaliações
     */
    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarTodas() {
        List<AvaliacaoResponseDTO> avaliacoes = service.listarTodas();
        return ResponseEntity.ok(avaliacoes);
    }
    
    /**
     * ENDPOINT: BUSCAR AVALIAÇÃO POR ID
     * 
     * GET /avaliacoes/{id}
     * 
     * Exemplo: GET /avaliacoes/1
     * 
     * @PathVariable captura o ID da URL
     * 
     * Retorna HTTP 200 (OK) com a avaliação ou
     * HTTP 404 (Not Found) se não existir
     * 
     * @param id ID da avaliação
     * @return Avaliação encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        AvaliacaoResponseDTO avaliacao = service.buscarPorId(id);
        return ResponseEntity.ok(avaliacao);
    }
    
    /**
     * ENDPOINT: LISTAR AVALIAÇÕES POR PONTO TURÍSTICO
     * 
     * GET /avaliacoes/ponto/{pontoId}
     * 
     * Exemplo: GET /avaliacoes/ponto/5
     * 
     * Retorna todas as avaliações de um ponto turístico específico
     * Útil para mostrar avaliações na página de detalhes do ponto
     * 
     * Retorna HTTP 200 (OK) com lista de avaliações
     * 
     * @param pontoId ID do ponto turístico
     * @return Lista de avaliações do ponto turístico
     */
    @GetMapping("/ponto/{pontoId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorPontoTuristico(@PathVariable Long pontoId) {
        List<AvaliacaoResponseDTO> avaliacoes = service.listarPorPontoTuristico(pontoId);
        return ResponseEntity.ok(avaliacoes);
    }
    
    /**
     * ENDPOINT: ATUALIZAR AVALIAÇÃO
     * 
     * PUT /avaliacoes/{id}
     * 
     * Exemplo: PUT /avaliacoes/1
     * 
     * Recebe um JSON no body com os novos dados da avaliação
     * Atualiza todos os campos, mantendo ID e data original
     * 
     * Retorna HTTP 200 (OK) com avaliação atualizada ou
     * HTTP 404 (Not Found) se não existir
     * 
     * @param id ID da avaliação a atualizar
     * @param dto Novos dados
     * @return Avaliação atualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO avaliacao = service.atualizar(id, dto);
        return ResponseEntity.ok(avaliacao);
    }
    
    /**
     * ENDPOINT: EXCLUIR AVALIAÇÃO
     * 
     * DELETE /avaliacoes/{id}
     * 
     * Exemplo: DELETE /avaliacoes/1
     * 
     * Remove permanentemente a avaliação do banco de dados
     * 
     * Retorna HTTP 204 (No Content) se excluído com sucesso ou
     * HTTP 404 (Not Found) se não existir
     * 
     * @param id ID da avaliação a excluir
     * @return Resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
