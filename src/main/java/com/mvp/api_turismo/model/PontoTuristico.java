package com.mvp.api_turismo.model;

import com.mvp.api_turismo.Enum.TipoPontoTuristico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pontos_turisticos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PontoTuristico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoPontoTuristico tipo;

    private String localizacao;
    private String nivelDificuldade;
    private String imagemUrl;
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
    }
}
