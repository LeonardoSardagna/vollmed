package com.volmed.vollmed_api.main.medico;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Especialidade {
    ORTOPEDIA ("Ortopedia"),
    CARDIOLOGIA ("Cardiologia"),
    GINECOLOGIA ("Ginecologia"),
    DERMATOLOGIA ("Dermatologia");

    private String especialidades;

    Especialidade(String especialidades) {
        this.especialidades = especialidades;
    }

    @JsonCreator
    public static Especialidade fromString(String especialidade){
        for (Especialidade categoria : Especialidade.values()){
            if (categoria.especialidades.equalsIgnoreCase(especialidade)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Especialidade não encontrada. " + especialidade);
    }
}
