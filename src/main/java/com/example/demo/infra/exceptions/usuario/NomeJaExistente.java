package com.example.demo.infra.exceptions.usuario;

public class NomeJaExistente extends RuntimeException{

    public NomeJaExistente(){
        super("Nome já cadastrado");
    }

}
