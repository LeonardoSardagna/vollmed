package com.volmed.vollmed_api.main.consulta.agendamento;

import com.volmed.vollmed_api.main.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        Especialidade especialidade,
        @NotNull
        @Future
        LocalDateTime data) {
}
