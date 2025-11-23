package com.avaliacoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long pontoTuristicoId;
    
    @Column(nullable = false)
    private Long usuarioId;
    
    @Column(nullable = false)
    private Integer nota;
    
    @Column(columnDefinition = "TEXT")
    private String comentario;
    
    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;
}
