package com.volmed.vollmed_api.main.consulta;

import com.volmed.vollmed_api.infra.exception.ValidacaoException;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosDetalhamentoConsulta;
import com.volmed.vollmed_api.main.medico.Medico;
import com.volmed.vollmed_api.main.medico.MedicoRepository;
import com.volmed.vollmed_api.main.paciente.PacienteRepository;
import com.volmed.vollmed_api.main.validacoes.validacaoAgendamento.IValidacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleConsultasService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<IValidacoes> listaValidacoesConsulta;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Médico não encontrado");
        }

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente não encontrado");
        }

        listaValidacoesConsulta.forEach(v -> v.validar(dados));

        var medico = escolherMedicoAleatorio(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedicoAleatorio(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("A especialidade é obrigatória quando o médico não é inserido");
        }

        return medicoRepository.escolherMedicoAleatorioDataLivre(dados.especialidade(), dados.data());

    }
}
