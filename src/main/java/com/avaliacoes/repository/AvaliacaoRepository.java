package com.avaliacoes.repository;

import com.avaliacoes.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * REPOSITORY - INTERFACE DE ACESSO AO BANCO DE DADOS
 * 
 * Esta interface usa Spring Data JPA para facilitar operações no banco de dados.
 * Não é necessário implementar os métodos - o Spring cria a implementação automaticamente.
 * 
 * JpaRepository<Avaliacao, Long> fornece automaticamente métodos como:
 * - save(): inserir ou atualizar
 * - findById(): buscar por ID
 * - findAll(): buscar todos
 * - deleteById(): excluir por ID
 * - existsById(): verificar se existe
 * - count(): contar registros
 * 
 * Além disso, podemos criar métodos customizados seguindo convenções de nomes.
 * O Spring Data JPA gera automaticamente a query SQL baseada no nome do método.
 */
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    /**
     * BUSCAR AVALIAÇÕES POR PONTO TURÍSTICO
     * 
     * Este método busca todas as avaliações de um determinado ponto turístico.
     * Útil para mostrar todas as avaliações quando o usuário visita a página
     * de um ponto turístico específico.
     * 
     * O nome do método segue a convenção do Spring Data JPA:
     * - findBy: indica uma busca
     * - PontoTuristicoId: nome do campo na entidade
     * 
     * O Spring Data JPA gera automaticamente a query:
     * SELECT * FROM avaliacoes WHERE ponto_turistico_id = ?
     * 
     * @param pontoTuristicoId ID do ponto turístico
     * @return Lista de avaliações do ponto turístico
     */
    List<Avaliacao> findByPontoTuristicoId(Long pontoTuristicoId);
    
    /**
     * BUSCAR AVALIAÇÕES POR USUÁRIO
     * 
     * Este método busca todas as avaliações feitas por um usuário específico.
     * Útil para mostrar o histórico de avaliações de um usuário.
     * 
     * Query SQL gerada automaticamente:
     * SELECT * FROM avaliacoes WHERE usuario_id = ?
     * 
     * @param usuarioId ID do usuário
     * @return Lista de avaliações do usuário
     */
    List<Avaliacao> findByUsuarioId(Long usuarioId);
    
    /**
     * BUSCAR AVALIAÇÕES POR PONTO TURÍSTICO E NOTA
     * 
     * Este método busca avaliações de um ponto turístico com uma nota específica.
     * Útil para filtrar avaliações, por exemplo, mostrar apenas avaliações 5 estrelas.
     * 
     * Query SQL gerada automaticamente:
     * SELECT * FROM avaliacoes WHERE ponto_turistico_id = ? AND nota = ?
     * 
     * @param pontoTuristicoId ID do ponto turístico
     * @param nota Nota da avaliação (1 a 5)
     * @return Lista de avaliações filtradas
     */
    List<Avaliacao> findByPontoTuristicoIdAndNota(Long pontoTuristicoId, Integer nota);
}
