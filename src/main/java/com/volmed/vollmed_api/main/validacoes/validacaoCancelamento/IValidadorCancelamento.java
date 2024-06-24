package com.volmed.vollmed_api.main.validacoes.validacaoCancelamento;

import com.volmed.vollmed_api.main.consulta.cancelamento.DadosCancelamentoConsulta;

public interface IValidadorCancelamento {
    void validar(DadosCancelamentoConsulta dados);
}
