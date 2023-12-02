package com.example.demo.repositories;

import com.example.demo.entities.Lendo;
import com.example.demo.records.lendo.LendoSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LendoRepository extends JpaRepository<Lendo, Long> {

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida) FROM lendo l JOIN l.usuarioId u WHERE u.id = :id")
    List<LendoSaida> findByUsuarioId(Long id);

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida) FROM lendo l WHERE l.id = :id")
    Optional<LendoSaida> findByIdLen(Long id);

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida) FROM lendo l")
    List<LendoSaida> findAllLen();

}
