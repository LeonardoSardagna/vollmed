package com.volmed.vollmed_api.main.consulta.cancelamento;

import com.volmed.vollmed_api.main.consulta.ConsultaRepository;
import com.volmed.vollmed_api.main.validacoes.validacaoCancelamento.IValidadorCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ControleCancelamentoService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    private List<IValidadorCancelamento> cancelamentoList;

    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.idConsulta())){
            System.out.println("Consulta não encontrada. Verifiue os dados.");
        }

        cancelamentoList.forEach(c -> c.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());
    }
}
