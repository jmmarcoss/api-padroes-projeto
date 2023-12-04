package com.example.demo.records.lendo;

import java.util.Date;

public record LendoEntrada(Long usuarioId, Long livroId, Date dataInicioDeLeitura, Date dataTerminoDeLietura, int minutos, Double tempoMedioPorPagina, Double porcentagemLida, int qntDePaginas) {
}
