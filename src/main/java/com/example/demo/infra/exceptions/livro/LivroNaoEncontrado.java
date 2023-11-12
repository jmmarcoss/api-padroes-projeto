package com.example.demo.infra.exceptions.livro;

public class LivroNaoEncontrado extends RuntimeException{

    public LivroNaoEncontrado(){
        super("Livro não encontrado");
    }
}
