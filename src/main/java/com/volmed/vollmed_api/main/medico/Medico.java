package com.volmed.vollmed_api.main.medico;

import com.volmed.vollmed_api.main.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = Especialidade.fromString(String.valueOf(dados.especialidade()));
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarCadastro(DadosAtualizaMedico dados) {
        if (dados.nome() != null && !dados.nome().isBlank()){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null && !dados.telefone().isBlank()){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco = dados.endereco();
        }
    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
