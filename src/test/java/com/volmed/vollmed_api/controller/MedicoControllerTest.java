package com.volmed.vollmed_api.controller;

import com.volmed.vollmed_api.main.endereco.DadosEndereco;
import com.volmed.vollmed_api.main.endereco.Endereco;
import com.volmed.vollmed_api.main.medico.*;
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
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicoRepository medicoRepository;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJacksonTester;

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 400 quando os dados estiverem inválidos")
    void cadastroCase1() throws Exception{
        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 201 quando os dados estiverem válidos")
    void cadastroCase2() throws Exception{
        var dadosCadastro = cadastroMedico();

        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJacksonTester.write(new DadosCadastroMedico(
                        "teste1",
                        "test1@gmail.com",
                        "5547992069737",
                        "1234",
                        Especialidade.CARDIOLOGIA,
                        dadosEndereco())).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJacksonTester.write(dadosMedico());

        assertThat(jsonEsperado.getJson()).isEqualTo(response.getContentAsString());
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

    private DadosDetalhamentoMedico dadosMedico(){
        return new DadosDetalhamentoMedico(
                null,
                "teste1",
                "test1@gmail.com",
                "5547992069737",
                "1234",
                Especialidade.CARDIOLOGIA,
                new Endereco(dadosEndereco())
        );
    }

    private DadosCadastroMedico cadastroMedico(){
        return new DadosCadastroMedico(
                "teste1",
                "test1@gmail.com",
                "5547992069737",
                "1234",
                Especialidade.CARDIOLOGIA,
                dadosEndereco()
        );
    }
}