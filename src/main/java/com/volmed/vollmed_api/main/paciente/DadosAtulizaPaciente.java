package com.volmed.vollmed_api.main.paciente;

import com.volmed.vollmed_api.main.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtulizaPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco
) {
}
