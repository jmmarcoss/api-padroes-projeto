package com.example.demo.infra.exceptions.favorito;

public class FavoritoNaoExistente extends RuntimeException{

    public FavoritoNaoExistente(){
        super("Instância de favorito não encontrada");
    }
}
