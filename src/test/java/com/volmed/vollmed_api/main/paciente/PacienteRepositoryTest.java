package com.volmed.vollmed_api.main.paciente;

import com.volmed.vollmed_api.main.endereco.DadosEndereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TestEntityManager manager;

    @Test
    void findAtivoById() {
        Paciente paciente = cadastroPaciente();
        var testepaciente = pacienteRepository.findAtivoById(paciente.getId());

        assertThat(testepaciente).isTrue();
    }

    private Paciente cadastroPaciente(){
        Paciente newPaciente = new Paciente(dadosPaciente("leo", "leo@gmail.com", "122.123.123-98"));
        manager.persist(newPaciente);
        return newPaciente;
    }

    private DadosPaciente dadosPaciente(String nome, String email, String cpf){
        return new DadosPaciente(
                nome,
                email,
                "5547991867551",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "teste 1",
                "123",
                "teste 1",
                "teste 1",
                "teste 1",
                "SC",
                "12345-678"
        );
    }

}