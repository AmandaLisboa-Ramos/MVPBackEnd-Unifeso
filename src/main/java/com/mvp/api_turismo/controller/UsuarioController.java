package com.mvp.api_turismo.controller;

import com.mvp.api_turismo.dto.AtualizarDto;
import com.mvp.api_turismo.dto.UsuarioDto;
import com.mvp.api_turismo.model.Usuario;
import com.mvp.api_turismo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto dto){
        service.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(service.buscarUsuarioPorId(id));

    }
@GetMapping
    public ResponseEntity<List<Usuario>> buscarUsuario(){
  return ResponseEntity.ok(service.buscarUsuario());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AtualizarDto> atualizarUsuario(@PathVariable(name = "id") Integer id, @RequestBody AtualizarDto dto){
        service.atualizarUsuario(id,dto);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable(name = "id") Integer id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
