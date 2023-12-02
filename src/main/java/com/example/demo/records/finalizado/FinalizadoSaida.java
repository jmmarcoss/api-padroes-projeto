package com.example.demo.records.finalizado;

import com.example.demo.entities.Livro;

import java.util.Date;

public record FinalizadoSaida(Long id, Livro livroId, Date dataInicioDeLeitura, Date dataTerminoDeLietura, int minutos, Double tempoMedioPorPagina) {
}
