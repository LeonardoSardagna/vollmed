package com.volmed.vollmed_api.main.paciente;

import com.volmed.vollmed_api.main.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(DadosPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarCadastro(DadosAtulizaPaciente dados) {
        if (dados.nome() != null && !dados.nome().isBlank()){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null && !dados.telefone().isBlank()){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco = dados.endereco();
        }
    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
