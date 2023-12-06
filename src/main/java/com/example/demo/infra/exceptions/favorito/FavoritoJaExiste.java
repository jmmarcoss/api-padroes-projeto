package com.example.demo.infra.exceptions.favorito;

public class FavoritoJaExiste extends RuntimeException {

    public FavoritoJaExiste(){
        super("Instância do favorito já existe");
    }
}
