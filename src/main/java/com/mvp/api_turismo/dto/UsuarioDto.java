package com.mvp.api_turismo.dto;

import com.mvp.api_turismo.Enum.TipoDeUsuario;

public record UsuarioDto(String nome, String email, String password, TipoDeUsuario tipoDeUsuario) {
}
