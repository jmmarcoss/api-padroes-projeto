package com.example.demo.infra.exceptions.finalizado;

public class FinalizadoJaExiste extends RuntimeException{

    public FinalizadoJaExiste(){
        super("Instância já existente de finalizado");
    }
}
