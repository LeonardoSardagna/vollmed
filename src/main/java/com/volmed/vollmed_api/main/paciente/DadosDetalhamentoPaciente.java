package com.volmed.vollmed_api.main.paciente;

import com.volmed.vollmed_api.main.endereco.Endereco;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente dados) {
        this(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCpf(),
                dados.getEndereco());
    }
}
