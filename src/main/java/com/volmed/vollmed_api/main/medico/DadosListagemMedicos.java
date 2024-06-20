package com.volmed.vollmed_api.main.medico;

public record DadosListagemMedicos(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedicos (Medico dados){
        this(dados.getNome(), dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}
