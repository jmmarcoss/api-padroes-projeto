package com.example.demo.infra.exceptions;

public class NomeJaExistente extends RuntimeException{

    public NomeJaExistente(){
        super("Nome já cadastrado");
    }

}
