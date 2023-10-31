package com.example.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static TokenService instance;

    private TokenService(){}

    public static TokenService getInstance(){
        if (instance == null){
            instance = new TokenService();
        }
        return instance;
    }

    public String gerarToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256("senhaaleatoriaporenquanto");
            return JWT.create()
                    .withIssuer("Prateleira")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }
    public String verificarToken(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256("senhaaleatoriaporenquanto");
            return JWT.require(algorithm)
                    .withIssuer("Prateleira")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
