package com.volmed.vollmed_api.main.medico;

import com.volmed.vollmed_api.main.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco
) {
}
