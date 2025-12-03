package com.avaliacoes.repository;

import com.avaliacoes.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    List<Avaliacao> findByPontoTuristicoId(Long pontoTuristicoId);
    
    List<Avaliacao> findByUsuarioId(Long usuarioId);
    
    List<Avaliacao> findByPontoTuristicoIdAndNota(Long pontoTuristicoId, Integer nota);
}
