package com.example.demo.records.finalizado;

import java.util.Date;

public record FinalizadoEntrada(Long usuarioId, Long livroId, Date dataInicioDeLeitura, Date dataTerminoDeLietura, int minutos, Double tempoMedioPorPagina) {
}
