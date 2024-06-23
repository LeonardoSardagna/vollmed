package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoInativo implements IValidacoes{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!medicoAtivo){
            throw new ValidacaoException("Medico está inativo no sistema");
        }
    }
}
