package com.example.demo.builders;

import com.example.demo.entities.Finalizado;
import com.example.demo.entities.Livro;
import com.example.demo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FinalizadoBuilder {

    private Finalizado finalizado;

    public FinalizadoBuilder(){
        this.finalizado = new Finalizado();
    }

    public FinalizadoBuilder setUsuarioId(Usuario usuario){
        this.finalizado.setUsuarioId(usuario);
        return this;
    }

    public FinalizadoBuilder setLivroId(Livro livro){
        this.finalizado.setLivroId(livro);
        return this;
    }

    public FinalizadoBuilder setDataInicioDeLeitura(Date data){
        this.finalizado.setDataInicioDeLeitura(data);
        return this;
    }

    public FinalizadoBuilder setDataTerminoDeLeitura(Date data){
        this.finalizado.setDataTerminoDeLeitura(data);
        return this;
    }

    public FinalizadoBuilder setMinutos(int minutos){
        this.finalizado.setMinutos(minutos);
        return this;
    }

    public FinalizadoBuilder setTempoMedioPorPagina(Double tempo){
        this.finalizado.setTempoMedioPorPagina(tempo);
        return this;
    }

    public Finalizado build(){
        return this.finalizado;
    }

}
