package com.example.demo.infra.exceptions;

import com.example.demo.infra.exceptions.usuario.EmailJaExistente;
import com.example.demo.infra.exceptions.usuario.NomeJaExistente;
import com.example.demo.infra.exceptions.usuario.UsuarioNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class tratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity usuarioNaoEncontrado(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmailJaExistente.class)
    public ResponseEntity emailJaExistente(){
        return ResponseEntity.badRequest().body("Email já existente");
    }

    @ExceptionHandler(NomeJaExistente.class)
    public ResponseEntity nomeJaExistente(){
        return ResponseEntity.badRequest().body("Nome já existente");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadoErroValidacao::new).toList());
    }

    private record DadoErroValidacao(String campo, String mensagem){
        public DadoErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
