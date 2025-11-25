package com.mvp.api_turismo.service;

import com.mvp.api_turismo.Repository.UsuarioRepository;
import com.mvp.api_turismo.dto.AtualizarDto;
import com.mvp.api_turismo.dto.UsuarioDto;
import com.mvp.api_turismo.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void cadastrarUsuario(UsuarioDto usuario){
        if (usuario == null){
            throw new IllegalArgumentException("dados do usuario não podem ser nulos");
        }
        Usuario u = new Usuario();
        u.setNome(usuario.nome());
        u.setEmail(usuario.email());
        u.setPassword(usuario.password());
        u.setTipoDeUsuario(usuario.tipoDeUsuario());
        repository.save(u);

    }


    public Usuario buscarUsuarioPorId(Integer id ){
        return repository.findById(id).orElseThrow(()->
                new NullPointerException("usuario não encontrado " + id));
    }

    public List<Usuario> buscarUsuario(){
        return repository.findAll();
    }

    public void atualizarUsuario(Integer id, AtualizarDto usuario){
      if (usuario == null){
          throw new IllegalArgumentException("dados do usuario não podem ser nulos");
      }
      Usuario user = buscarUsuarioPorId(id);
      user.setNome(usuario.nome());
        user.setEmail(usuario.email());
        user.setPassword(usuario.password());
        repository.save(user);
    }

    @Transactional
    public void deletarPorId(Integer id){
        repository.deleteById(id);
    }
}
