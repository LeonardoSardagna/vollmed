package com.volmed.vollmed_api.controller;

import com.volmed.vollmed_api.main.consulta.ControleConsultasService;
import com.volmed.vollmed_api.main.consulta.agendamento.DadosAgendamentoConsulta;
import com.volmed.vollmed_api.main.consulta.cancelamento.ControleCancelamentoService;
import com.volmed.vollmed_api.main.consulta.cancelamento.DadosCancelamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ControleConsultasService agendamentoConsultas;

    @Autowired
    private ControleCancelamentoService controleCancelamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendamentoConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deletar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        controleCancelamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
