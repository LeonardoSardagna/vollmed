package com.volmed.vollmed_api.main.validacoes.validacaoCancelamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.Consulta;
import com.volmed.vollmed_api.main.consulta.ConsultaRepository;
import com.volmed.vollmed_api.main.consulta.cancelamento.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidarHorarioCancelamento implements IValidadorCancelamento{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var dataAtual = LocalDateTime.now();

        if (dataAtual.plusHours(24).isAfter(consulta.getData())){
            throw new ValidacaoException("Cancelamento de consultas devem ser realizadas 24 horas antes da consulta");
        }

    }
}
