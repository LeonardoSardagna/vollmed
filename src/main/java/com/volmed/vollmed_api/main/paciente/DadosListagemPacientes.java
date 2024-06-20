package com.volmed.vollmed_api.main.paciente;

public record DadosListagemPacientes(
        String nome,
        String email,
        String cpf) {

    public DadosListagemPacientes (Paciente dados){
        this(dados.getNome(), dados.getEmail(), dados.getCpf());
    }
}
