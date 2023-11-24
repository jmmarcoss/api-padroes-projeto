package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity(name = "finalizados")
@Table(name = "finalizados")
public class Finalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livroId;
    @Column(name = "data_inicio_de_leitura")
    private Date dataInicioDeLeitura;
    @Column(name = "data_termino_de_leitura")
    private Date dataTerminoDeLeitura;
    private int minutos;
    @Column(name = "tempo_medio_por_pagina")
    private Double tempoMedioPorPagina;


    public Finalizado(Usuario usuario, Livro livro, Date date, Date date1, int minutos, Double aDouble) {
        this.usuarioId = usuario;
        this.livroId = livro;
        this.dataInicioDeLeitura = date;
        this.dataTerminoDeLeitura = date1;
        this.minutos = minutos;
        this.tempoMedioPorPagina = aDouble;
    }
}
