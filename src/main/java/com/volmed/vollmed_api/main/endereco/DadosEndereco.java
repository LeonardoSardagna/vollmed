package com.volmed.vollmed_api.main.endereco;

import jakarta.validation.constraints.NotNull;

public record DadosEndereco(
        @NotNull
        String logradouro,
        String numero,
        String complemento,
        @NotNull
        String bairro,
        @NotNull
        String cidade,
        @NotNull
        String uf,
        @NotNull
        String cep) {
}
