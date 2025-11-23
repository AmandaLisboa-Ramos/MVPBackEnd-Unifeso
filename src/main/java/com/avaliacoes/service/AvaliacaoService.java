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

/**
 * CAMADA SERVICE - LÓGICA DE NEGÓCIO
 * 
 * Esta classe contém as regras de negócio e validações da aplicação.
 * O Service fica entre o Controller (que recebe requisições) e o Repository
 * (que acessa o banco de dados).
 * 
 * Responsabilidades:
 * - Validar dados antes de salvar no banco
 * - Aplicar regras de negócio (ex: nota entre 1 e 5)
 * - Converter entre DTO e Entity
 * - Orquestrar operações complexas
 * 
 * @Service marca esta classe como um componente de serviço gerenciado pelo Spring
 */
@Service
public class AvaliacaoService {
    
    /**
     * Injeção de dependência do Repository
     * @Autowired faz o Spring fornecer automaticamente uma instância
     */
    @Autowired
    private AvaliacaoRepository repository;
    
    /**
     * CRIAR NOVA AVALIAÇÃO
     * 
     * Fluxo:
     * 1. Recebe DTO com dados do cliente
     * 2. Valida os dados (nota entre 1-5)
     * 3. Converte DTO → Entity
     * 4. Define data/hora atual
     * 5. Salva no banco via Repository
     * 6. Converte Entity → DTO de resposta
     * 7. Retorna DTO ao Controller
     * 
     * @param dto Dados da nova avaliação
     * @return DTO com a avaliação criada (incluindo ID e data)
     */
    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        validarNota(dto.getNota());
        
        Avaliacao avaliacao = converterDtoParaEntity(dto);
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        
        Avaliacao avaliacaoSalva = repository.save(avaliacao);
        
        return converterEntityParaDto(avaliacaoSalva);
    }
    
    /**
     * LISTAR TODAS AS AVALIAÇÕES
     * 
     * Busca todas as avaliações no banco e converte para DTO
     * 
     * @return Lista de todas as avaliações
     */
    public List<AvaliacaoResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::converterEntityParaDto)
                .collect(Collectors.toList());
    }
    
    /**
     * BUSCAR AVALIAÇÃO POR ID
     * 
     * @param id ID da avaliação
     * @return DTO da avaliação encontrada
     * @throws ResourceNotFoundException se não encontrar
     */
    public AvaliacaoResponseDTO buscarPorId(Long id) {
        Avaliacao avaliacao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Avaliação com ID " + id + " não encontrada"));
        
        return converterEntityParaDto(avaliacao);
    }
    
    /**
     * LISTAR AVALIAÇÕES POR PONTO TURÍSTICO
     * 
     * Busca todas as avaliações de um ponto turístico específico
     * 
     * @param pontoTuristicoId ID do ponto turístico
     * @return Lista de avaliações do ponto turístico
     */
    public List<AvaliacaoResponseDTO> listarPorPontoTuristico(Long pontoTuristicoId) {
        return repository.findByPontoTuristicoId(pontoTuristicoId)
                .stream()
                .map(this::converterEntityParaDto)
                .collect(Collectors.toList());
    }
    
    /**
     * ATUALIZAR AVALIAÇÃO
     * 
     * Fluxo:
     * 1. Busca avaliação existente pelo ID
     * 2. Valida novos dados
     * 3. Atualiza campos (mantém ID e data original)
     * 4. Salva no banco
     * 5. Retorna DTO atualizado
     * 
     * @param id ID da avaliação a atualizar
     * @param dto Novos dados
     * @return DTO com avaliação atualizada
     * @throws ResourceNotFoundException se não encontrar
     */
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
    
    /**
     * EXCLUIR AVALIAÇÃO
     * 
     * @param id ID da avaliação a excluir
     * @throws ResourceNotFoundException se não encontrar
     */
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Avaliação com ID " + id + " não encontrada");
        }
        
        repository.deleteById(id);
    }
    
    /**
     * VALIDAR NOTA
     * 
     * Regra de negócio: nota deve estar entre 1 e 5
     * 
     * @param nota Nota a validar
     * @throws InvalidDataException se nota inválida
     */
    private void validarNota(Integer nota) {
        if (nota == null || nota < 1 || nota > 5) {
            throw new InvalidDataException(
                    "A nota deve estar entre 1 e 5. Valor informado: " + nota);
        }
    }
    
    /**
     * CONVERTER DTO → ENTITY
     * 
     * Transforma objeto de requisição em entidade do banco
     * 
     * @param dto DTO de requisição
     * @return Entity Avaliacao
     */
    private Avaliacao converterDtoParaEntity(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPontoTuristicoId(dto.getPontoTuristicoId());
        avaliacao.setUsuarioId(dto.getUsuarioId());
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        return avaliacao;
    }
    
    /**
     * CONVERTER ENTITY → DTO
     * 
     * Transforma entidade do banco em objeto de resposta
     * 
     * @param entity Entity Avaliacao
     * @return DTO de resposta
     */
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
