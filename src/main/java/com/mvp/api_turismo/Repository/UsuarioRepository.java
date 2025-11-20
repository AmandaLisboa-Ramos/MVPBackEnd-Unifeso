package com.mvp.api_turismo.Repository;

import com.mvp.api_turismo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
