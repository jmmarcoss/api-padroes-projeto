package com.example.demo.builders;

import com.example.demo.entities.Finalizado;
import com.example.demo.entities.Lendo;
import com.example.demo.entities.Livro;
import com.example.demo.entities.Usuario;

import java.util.Date;

public class LendoBuilder {

    private Lendo lendo;

    public LendoBuilder(){
        this.lendo = new Lendo();
    }

    public LendoBuilder setUsuarioId(Usuario usuario){
        this.lendo.setUsuarioId(usuario);
        return this;
    }

    public LendoBuilder setLivroId(Livro livro){
        this.lendo.setLivroId(livro);
        return this;
    }

    public LendoBuilder setDataInicioDeLeitura(Date data){
        this.lendo.setDataInicioDeLeitura(data);
        return this;
    }

    public LendoBuilder setDataTerminoDeLeitura(Date data){
        this.lendo.setDataTerminoDeLeitura(data);
        return this;
    }

    public LendoBuilder setMinutos(int minutos){
        this.lendo.setMinutos(minutos);
        return this;
    }

    public LendoBuilder setTempoMedioPorPagina(Double tempo){
        this.lendo.setTempoMedioPorPagina(tempo);
        return this;
    }

    public LendoBuilder setPorcentagemLida(Double porc){
        this.lendo.setPorcentagemLida(porc);
        return this;
    }

    public LendoBuilder setQtdDePaginas(int qntdDePaginas){
        this.lendo.setQntDePaginas(qntdDePaginas);
        return this;
    }



    public Lendo build(){
        return this.lendo;
    }
}
