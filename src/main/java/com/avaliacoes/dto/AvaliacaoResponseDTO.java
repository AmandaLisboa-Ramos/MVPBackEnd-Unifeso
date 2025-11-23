package com.avaliacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoResponseDTO {
    
    private Long id;
    private Long pontoTuristicoId;
    private Long usuarioId;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
}
