package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioAtendimento implements IValidacoes{
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbrir = dataConsulta.getHour() < 7;
        var depoisDeFechar = dataConsulta.getHour() > 18;

        if (domingo || antesDeAbrir || depoisDeFechar){
            throw new ValidacaoException("Fora do horário de atendimento da clinica");
        }
    }
}
