package com.volmed.vollmed_api.main.consulta.cancelamento;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CancelamentoMotivo {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Medico cancelou"),
    OUTROS("Outros");

    private String motivos;

    CancelamentoMotivo(String motivo) {
        this.motivos = motivo;
    }

    @JsonCreator
    public static CancelamentoMotivo fromString(String motivo){
        for (CancelamentoMotivo categoria : CancelamentoMotivo.values()){
            if (categoria.motivos.equalsIgnoreCase(motivo.replace("_", " "))){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Motivo não encontrado: " + motivo);
    }
}
