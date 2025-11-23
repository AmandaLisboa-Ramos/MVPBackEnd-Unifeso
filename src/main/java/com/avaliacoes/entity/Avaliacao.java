package com.avaliacoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ENTIDADE AVALIACAO
 * 
 * Esta classe representa uma avaliação de ponto turístico no banco de dados.
 * Cada avaliação contém:
 * - Identificador único
 * - Referência ao ponto turístico avaliado
 * - Referência ao usuário que fez a avaliação
 * - Nota de 1 a 5
 * - Comentário opcional
 * - Data e hora da avaliação
 * 
 * A anotação @Entity marca esta classe como uma tabela do banco de dados.
 * O JPA/Hibernate criará automaticamente a tabela 'avaliacoes' com base nesta classe.
 */
@Entity
@Table(name = "avaliacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    
    /**
     * ID - Identificador único da avaliação
     * @GeneratedValue indica que o valor é gerado automaticamente pelo banco
     * GenerationType.IDENTITY usa auto-increment do banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * PONTO TURISTICO ID - Referência ao ponto turístico avaliado
     * Armazena o ID do ponto turístico (será relacionado com a entidade
     * criada por outros integrantes do grupo)
     * @Column(nullable = false) indica que este campo é obrigatório
     */
    @Column(nullable = false)
    private Long pontoTuristicoId;
    
    /**
     * USUARIO ID - Referência ao usuário que fez a avaliação
     * Armazena o ID do usuário (será relacionado com a entidade
     * criada por outros integrantes do grupo)
     * @Column(nullable = false) indica que este campo é obrigatório
     */
    @Column(nullable = false)
    private Long usuarioId;
    
    /**
     * NOTA - Avaliação numérica de 1 a 5 estrelas
     * A validação do intervalo 1-5 é feita na camada Service
     * @Column(nullable = false) indica que este campo é obrigatório
     */
    @Column(nullable = false)
    private Integer nota;
    
    /**
     * COMENTARIO - Texto opcional com a opinião do usuário
     * Pode conter impressões, sugestões ou críticas sobre o ponto turístico
     * @Column(columnDefinition = "TEXT") permite textos longos
     */
    @Column(columnDefinition = "TEXT")
    private String comentario;
    
    /**
     * DATA AVALIACAO - Data e hora em que a avaliação foi criada
     * É preenchido automaticamente no momento da criação
     * @Column(nullable = false) indica que este campo é obrigatório
     */
    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;
}
