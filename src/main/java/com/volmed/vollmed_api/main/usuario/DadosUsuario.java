package com.volmed.vollmed_api.main.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosUsuario(
        @NotBlank
        String usuario,
        @NotBlank
        String senha) {
}
