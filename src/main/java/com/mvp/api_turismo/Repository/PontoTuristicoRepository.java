package com.mvp.api_turismo.Repository;

import com.mvp.api_turismo.model.PontoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long> {
}
