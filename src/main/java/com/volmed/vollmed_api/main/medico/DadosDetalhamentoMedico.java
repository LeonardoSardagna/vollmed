package com.volmed.vollmed_api.main.medico;

import com.volmed.vollmed_api.main.consulta.DadosDetalhamentoConsulta;
import com.volmed.vollmed_api.main.endereco.Endereco;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoMedico(Medico dados) {
        this(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCrm(),
                dados.getEspecialidade(),
                dados.getEndereco());
    }
}
