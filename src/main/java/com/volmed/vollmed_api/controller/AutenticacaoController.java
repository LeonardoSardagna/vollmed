package com.volmed.vollmed_api.controller;

import com.volmed.vollmed_api.infra.Security.TokenJWTService;
import com.volmed.vollmed_api.main.usuario.DadosUsuario;
import com.volmed.vollmed_api.main.usuario.Usuario;
import com.volmed.vollmed_api.main.usuario.dadosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenJWTService tokenJWTService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosUsuario dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenJWTService.gerarTokenJWT((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
    }
}
