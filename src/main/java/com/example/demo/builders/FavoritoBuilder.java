package com.example.demo.builders;

import com.example.demo.entities.Favorito;
import com.example.demo.entities.Livro;
import com.example.demo.entities.Usuario;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoBuilder {

    private Favorito favorito;

    public FavoritoBuilder(){
        this.favorito =  new Favorito();
    }

    public FavoritoBuilder setUsuarioId(Usuario usuario){
        this.favorito.setUsuarioId(usuario);
        return this;
    }

    public FavoritoBuilder setLivroId(Livro livro){
        this.favorito.setLivroId(livro);
        return this;
    }

    public Favorito build(){
        return this.favorito;
    }
}
