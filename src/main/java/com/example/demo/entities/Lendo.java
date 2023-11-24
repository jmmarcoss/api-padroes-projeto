package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity(name = "lendo")
public class Lendo {

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
    @Column(name = "porcentagem_lida")
    private Double porcentagemLida;
}
