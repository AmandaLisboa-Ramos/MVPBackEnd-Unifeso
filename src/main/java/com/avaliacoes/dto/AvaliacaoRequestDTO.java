package com.avaliacoes.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {
    
    @NotNull(message = "O ID do ponto turístico é obrigatório")
    @Positive(message = "O ID do ponto turístico deve ser um número positivo")
    private Long pontoTuristicoId;
    
    @NotNull(message = "O ID do usuário é obrigatório")
    @Positive(message = "O ID do usuário deve ser um número positivo")
    private Long usuarioId;
    
    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;
    
    @Size(max = 1000, message = "O comentário não pode ter mais de 1000 caracteres")
    private String comentario;
}
