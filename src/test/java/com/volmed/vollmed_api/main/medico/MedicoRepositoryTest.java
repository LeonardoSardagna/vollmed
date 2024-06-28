package com.volmed.vollmed_api.main.medico;

import com.volmed.vollmed_api.main.consulta.Consulta;
import com.volmed.vollmed_api.main.endereco.DadosEndereco;
import com.volmed.vollmed_api.main.paciente.DadosPaciente;
import com.volmed.vollmed_api.main.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Retorna null se o médico estiver com consulta marcada no dia")
    void escolherMedicoAleatorioDataLivreCase1() {
        var medico = cadastroMedico();
        var paciente = cadastroPaciente();
        var data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        cadastroConsulta(medico, paciente, data);

        var testeMedicoLivre = medicoRepository.escolherMedicoAleatorioDataLivre(Especialidade.CARDIOLOGIA, data);
        assertThat(testeMedicoLivre).isNull();
    }

    @Test
    @DisplayName("Retorna medico disponível na data")
    void escolherMedicoAleatorioDataLivreCase2() {
        var medico = cadastroMedico();
        var data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var testeMedicoLivre = medicoRepository.escolherMedicoAleatorioDataLivre(Especialidade.CARDIOLOGIA, data);

        assertThat(testeMedicoLivre).isEqualTo(medico);
    }

    @Test
    @DisplayName("Retorna true se o medico estiver ativo")
    void findAtivoByIdCase1(){
        var medico = cadastroMedico();
        var testeMedicoAtivo = medicoRepository.findAtivoById(medico.getId());
        assertThat(testeMedicoAtivo).isTrue();
    }

    private Medico cadastroMedico(){
        Medico newMedico = new Medico(dadosMedico("Leo", "leo@gmail.com", "123456", Especialidade.CARDIOLOGIA));
        manager.persist(newMedico);
        return newMedico;
    }

    private Paciente cadastroPaciente(){
        Paciente newPaciente = new Paciente(dadosPaciente("leo", "leo@gmail.com", "122.123.123-98"));
        manager.persist(newPaciente);
        return newPaciente;
    }

    private void cadastroConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        Consulta newConsulta = new Consulta(null, medico, paciente, data, null);
        manager.persist(newConsulta);
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade){
        return new DadosCadastroMedico(
                nome,
                email,
                "5544992069737",
                crm,
                especialidade,
                dadosEndereco()
        );
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
