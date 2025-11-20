package com.mvp.api_turismo.model;

import com.mvp.api_turismo.Enum.TipoDeUsuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private TipoDeUsuario tipoDeUsuario;
}
