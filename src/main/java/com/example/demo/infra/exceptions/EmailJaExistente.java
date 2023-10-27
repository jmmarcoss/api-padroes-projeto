package com.example.demo.infra.exceptions;

public class EmailJaExistente extends RuntimeException {

    public EmailJaExistente(){
        super("Email já cadastrado");
    }
}
