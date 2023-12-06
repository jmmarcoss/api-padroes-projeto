package com.example.demo.repositories;

import com.example.demo.entities.Finalizado;
import com.example.demo.records.finalizado.FinalizadoSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinalizadoRepository extends JpaRepository<Finalizado, Long> {

    @Query("SELECT NEW com.example.demo.records.finalizado.FinalizadoSaida(f.id, f.livroId, f.dataInicioDeLeitura, f.dataTerminoDeLeitura, f.minutos, f.tempoMedioPorPagina) FROM finalizados f JOIN f.usuarioId u WHERE u.id = :id")
    List<FinalizadoSaida> findByUsuarioId(Long id);

    @Query("SELECT NEW com.example.demo.records.finalizado.FinalizadoSaida(f.id, f.livroId, f.dataInicioDeLeitura, f.dataTerminoDeLeitura, f.minutos, f.tempoMedioPorPagina) FROM finalizados f")
    List<FinalizadoSaida> findAllFin();

    @Query("SELECT NEW com.example.demo.records.finalizado.FinalizadoSaida(f.id, f.livroId, f.dataInicioDeLeitura, f.dataTerminoDeLeitura, f.minutos, f.tempoMedioPorPagina) FROM finalizados f WHERE f.id = :id")
    Optional<FinalizadoSaida> findByIdFin(Long id);

    @Query("SELECT f FROM finalizados f WHERE f.livroId.id = :id")
    List<Finalizado> existirInstanciaDoLivro(Long id);

}
