package com.volmed.vollmed_api.main.consulta.cancelamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        CancelamentoMotivo motivo) {
}
