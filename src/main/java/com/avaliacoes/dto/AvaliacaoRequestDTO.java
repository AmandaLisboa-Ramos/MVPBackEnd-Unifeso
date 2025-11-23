package com.avaliacoes.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO DE REQUISIÇÃO - AVALIACAO REQUEST DTO
 * 
 * Este DTO (Data Transfer Object) é usado para RECEBER dados do cliente
 * quando uma nova avaliação é criada ou atualizada via API REST.
 * 
 * DTOs são objetos intermediários que transportam dados entre camadas,
 * protegendo a entidade e permitindo validações específicas.
 * 
 * Contém apenas os campos que o usuário pode informar:
 * - pontoTuristicoId: qual ponto turístico está sendo avaliado
 * - usuarioId: quem está fazendo a avaliação
 * - nota: avaliação de 1 a 5 estrelas
 * - comentario: opinião opcional do usuário
 * 
 * Nota: O ID e dataAvaliacao NÃO estão aqui porque são gerados automaticamente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {
    
    /**
     * PONTO TURISTICO ID - Obrigatório
     * @NotNull: garante que o campo não seja nulo
     * @Positive: garante que seja um número positivo (maior que 0)
     */
    @NotNull(message = "O ID do ponto turístico é obrigatório")
    @Positive(message = "O ID do ponto turístico deve ser um número positivo")
    private Long pontoTuristicoId;
    
    /**
     * USUARIO ID - Obrigatório
     * @NotNull: garante que o campo não seja nulo
     * @Positive: garante que seja um número positivo (maior que 0)
     */
    @NotNull(message = "O ID do usuário é obrigatório")
    @Positive(message = "O ID do usuário deve ser um número positivo")
    private Long usuarioId;
    
    /**
     * NOTA - Obrigatório, deve estar entre 1 e 5
     * @NotNull: garante que a nota não seja nula
     * @Min(1): nota mínima é 1 estrela
     * @Max(5): nota máxima é 5 estrelas
     */
    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;
    
    /**
     * COMENTARIO - Opcional
     * @Size: limita o tamanho do comentário entre 0 e 1000 caracteres
     * Pode ser null, pois o comentário é opcional
     */
    @Size(max = 1000, message = "O comentário não pode ter mais de 1000 caracteres")
    private String comentario;
}
