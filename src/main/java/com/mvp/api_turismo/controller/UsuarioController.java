package com.mvp.api_turismo.controller;

import com.mvp.api_turismo.dto.UsuarioDto;
import com.mvp.api_turismo.model.Usuario;
import com.mvp.api_turismo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto dto){
        service.cadastrarUsuario(dto);
        return  ResponseEntity.accepted().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(service.buscarUsuarioPorId(id));

    }
    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable(name = "id") Integer id,@RequestBody UsuarioDto dto){
        service.atualizarUsuario(id,dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable(name = "id") Integer id){
        service.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
