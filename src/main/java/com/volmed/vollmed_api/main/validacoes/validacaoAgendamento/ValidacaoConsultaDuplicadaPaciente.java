package com.volmed.vollmed_api.main.validacoes.validacaoAgendamento;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.ConsultaRepository;
import com.volmed.vollmed_api.main.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoConsultaDuplicadaPaciente implements IValidacoes{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var verificador = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (verificador){
            throw new ValidacaoException("Paciente já possui consulta neste dia");
        }
    }
}
