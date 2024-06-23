package com.volmed.vollmed_api.controller;

import com.volmed.vollmed_api.main.consulta.ControleAgendamentoConsultas;
import com.volmed.vollmed_api.main.consulta.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.medico.DadosDetalhamentoMedico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ControleAgendamentoConsultas agendamentoConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendamentoConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}
