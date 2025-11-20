package com.mvp.api_turismo.service;

import com.mvp.api_turismo.Repository.UsuarioRepository;
import com.mvp.api_turismo.dto.UsuarioDto;
import com.mvp.api_turismo.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void cadastrarUsuario(UsuarioDto usuario){
        Usuario u = new Usuario();
        u.setNome(u.getNome());
        u.setEmail(u.getEmail());
        u.setPassword(u.getPassword());
        u.setTipoDeUsuario(u.getTipoDeUsuario());
        repository.save(u);

    }


    public Usuario buscarUsuarioPorId(Integer id ){
        return repository.findById(id).orElseThrow(()->
                new NullPointerException("usuario não encontrado " + id));
    }

    public void atualizarUsuario(Integer id, UsuarioDto usuario){
        Usuario user = buscarUsuarioPorId(id);
        user.setId(user.getId());
        repository.save(user);
    }

    @Transactional
    public void deletarPorId(Integer id){
        repository.deleteById(id);
    }
}
