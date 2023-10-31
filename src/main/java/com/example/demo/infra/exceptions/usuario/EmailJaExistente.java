package com.example.demo.infra.exceptions.usuario;

public class EmailJaExistente extends RuntimeException {

    public EmailJaExistente(){
        super("Email já cadastrado");
    }
}
