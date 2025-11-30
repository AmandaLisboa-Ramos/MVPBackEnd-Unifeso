package com.mvp.api_turismo.dto;

import com.mvp.api_turismo.Enum.TipoPontoTuristico;

import java.time.LocalDateTime;

public record PontoTuristicoDto(
        Long id,
        String nome,
        String descricao,
        TipoPontoTuristico tipo,
        String localizacao,
        String nivelDificuldade,
        String imagemUrl,
        LocalDateTime dataCadastro) {
}
