package com.volmed.vollmed_api.main.validacoes.validacaoCancelamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.cancelamento.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class MotivoObrigatorio implements IValidadorCancelamento{

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        if (dados.motivo() == null){
            throw new ValidacaoException("O motivo da cancelamento é obrigatório");
        }
    }
}
