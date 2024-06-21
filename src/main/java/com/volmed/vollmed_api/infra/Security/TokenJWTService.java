package com.volmed.vollmed_api.infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.volmed.vollmed_api.main.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenJWTService {

    @Value("${token.security}")
    private String senha;

    public String gerarTokenJWT(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senha);
            return JWT.create()
                    .withIssuer("API VOLL MED")
                    .withSubject(usuario.getUsuario())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar tokenJWT");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senha);
            return JWT.require(algoritmo)
                    .withIssuer("API VOLL MED")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("TokenJWT inválido");
        }
    }

    private Instant tempoExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
