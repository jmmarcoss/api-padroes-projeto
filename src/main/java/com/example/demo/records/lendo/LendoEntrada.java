package com.example.demo.records.lendo;

import java.util.Date;

public record LendoEntrada(Long usuarioId, Long livroId, Date dataInicioDeLeitura, Date dataTerminoDeLeitura, double minutos, double qntDePaginas) {
}
