package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoAgendamentoConsultas implements IValidacoes{
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();
        var antecedencia = Duration.between(dataAtual, dataConsulta).toMinutes();
        System.out.println("data consulta: "+dataConsulta);
        System.out.println("data atual: "+ dataAtual);
        System.out.println("Diferença: "+ antecedencia);
        if(antecedencia < 30){
            throw new ValidacaoException("O agendamento de consultas requer antecedência de 30 minutos");
        }
    }
}
