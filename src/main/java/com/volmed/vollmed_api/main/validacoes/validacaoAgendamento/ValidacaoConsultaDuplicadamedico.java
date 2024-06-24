package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.ConsultaRepository;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoConsultaDuplicadamedico implements IValidacoes{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var verificador = consultaRepository.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());

        if (verificador){
            throw new ValidacaoException("Médico já possui consulta com paciente neste dia");
        }
    }
}
