package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteInativo implements IValidacoes{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("Paciente está inativo no sistema");
        }

    }
}
