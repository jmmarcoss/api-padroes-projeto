package com.example.demo.records.lendo;

import com.example.demo.entities.Livro;

import java.util.Date;

public record LendoSaida(Long id, Livro livroId, Date dataInicioDeLeitura, Date dataTerminoDeLietura, int minutos, Double tempoMedioPorPagina, Double porcentagemLida) {
}
