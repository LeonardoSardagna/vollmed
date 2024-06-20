package com.volmed.vollmed_api.main.medico;

import com.volmed.vollmed_api.main.endereco.DadosEndereco;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @Enumerated
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
