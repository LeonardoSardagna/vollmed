package com.volmed.vollmed_api.main.consulta;

import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosDetalhamentoConsulta;
import com.volmed.vollmed_api.main.medico.Especialidade;
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
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ControleConsultasServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ControleConsultasService controleConsultasService;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJacksonTester;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJacksonTester;

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 400 quando os dados estiverem inválidos")
    void agendarCase1() throws Exception {
        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON).content("{}")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar 200 quando os dados estiverem válidos")
    void agendarCase2() throws Exception {
        var data = LocalDateTime.now().plusDays(2);
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 2l, data);

        when(controleConsultasService.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJacksonTester
                                .write(new DadosAgendamentoConsulta(2l, 2l, Especialidade.CARDIOLOGIA, data))
                                .getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJacksonTester.write(dadosDetalhamento).getJson();

        assertThat(jsonEsperado).isEqualTo(response.getContentAsString());
    }
}
