package com.avaliacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO DE RESPOSTA - AVALIACAO RESPONSE DTO
 * 
 * Este DTO é usado para ENVIAR dados ao cliente como resposta da API REST.
 * 
 * Contém todos os campos da avaliação, incluindo:
 * - id: identificador único gerado pelo banco
 * - dataAvaliacao: data/hora gerada automaticamente
 * - todos os outros campos informados pelo usuário
 * 
 * Este DTO é criado a partir da entidade Avaliacao quando retornamos
 * dados para o cliente. Isso evita expor a entidade diretamente e
 * permite customizar quais informações são enviadas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoResponseDTO {
    
    /**
     * ID - Identificador único da avaliação (gerado pelo banco)
     */
    private Long id;
    
    /**
     * PONTO TURISTICO ID - Referência ao ponto turístico avaliado
     */
    private Long pontoTuristicoId;
    
    /**
     * USUARIO ID - Referência ao usuário que fez a avaliação
     */
    private Long usuarioId;
    
    /**
     * NOTA - Avaliação de 1 a 5 estrelas
     */
    private Integer nota;
    
    /**
     * COMENTARIO - Opinião opcional do usuário
     */
    private String comentario;
    
    /**
     * DATA AVALIACAO - Data e hora em que a avaliação foi criada
     */
    private LocalDateTime dataAvaliacao;
}
