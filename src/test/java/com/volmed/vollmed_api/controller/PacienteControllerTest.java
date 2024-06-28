package com.volmed.vollmed_api.controller;

import com.volmed.vollmed_api.main.endereco.DadosEndereco;
import com.volmed.vollmed_api.main.paciente.DadosDetalhamentoPaciente;
import com.volmed.vollmed_api.main.paciente.DadosPaciente;
import com.volmed.vollmed_api.main.paciente.Paciente;
import com.volmed.vollmed_api.main.paciente.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PacienteRepository pacienteRepository;

    @Autowired
    private JacksonTester<DadosPaciente> pacienteCadastroJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoPaciente> dadosDetalhamentoPacienteJacksonTester;

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 400 quando os dados forem inválidos")
    void cadastrarCase1() throws Exception{
        var response = mvc.perform(post("/pacientes")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 201 quando os dado forem válidos")
    void cadastrarCase2() throws Exception {

        var cadastroPaciente = dadosPaciente();
        when(pacienteRepository.save(any())).thenReturn(new Paciente(cadastroPaciente));

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteCadastroJacksonTester.write(dadosPaciente())
                        .getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = pacienteCadastroJacksonTester.write(cadastroPaciente);

        assertThat(jsonEsperado.getJson()).isEqualTo(response.getContentAsString());
    }

    private DadosPaciente dadosPaciente() {
        return new DadosPaciente(
                "teste1",
                "teste1@gmail.com",
                "5547992069737",
                "090.059.109-92",
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