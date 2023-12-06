package com.example.demo.repositories;

import com.example.demo.entities.Lendo;
import com.example.demo.records.lendo.LendoSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LendoRepository extends JpaRepository<Lendo, Long> {

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida, l.qntDePaginas) " +
            "FROM lendo l " +
            "WHERE (l.livroId.id, l.id) IN " +
            "  (SELECT l2.livroId.id, MAX(l2.id) FROM lendo l2 " +
            "   WHERE l2.usuarioId.id = :id AND l2.livroId.id NOT IN " +
            "     (SELECT f.livroId.id FROM finalizados f WHERE f.usuarioId.id = :id) " +
            "   GROUP BY l2.livroId.id) " +
            "AND l.usuarioId.id = :id")
    List<LendoSaida> findByUsuarioId(Long id);

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida, l.qntDePaginas) FROM lendo l WHERE l.id = :id")
    Optional<LendoSaida> findByIdLen(Long id);

    @Query("SELECT NEW com.example.demo.records.lendo.LendoSaida(l.id, l.livroId, l.dataInicioDeLeitura, l.dataTerminoDeLeitura, l.minutos, l.tempoMedioPorPagina, l.porcentagemLida, l.qntDePaginas) FROM lendo l")
    List<LendoSaida> findAllLen();

    @Query("SELECT SUM(l.minutos) FROM lendo l JOIN l.usuarioId u WHERE u.id = :id")
    Integer somaMinutosTotais(Long id);

    @Query("SELECT SUM(l.qntDePaginas) FROM lendo l JOIN l.usuarioId u WHERE u.id = :id")
    Integer somaPaginasTotais(Long id);

    @Query("SELECT SUM(l.qntDePaginas) / SUM(l.minutos) FROM lendo l WHERE l.usuarioId.id = :userId AND l.livroId.id = :livroId")
    Double calcularRazaoPaginasPorMinutos(Long userId, Long livroId);

    @Query("SELECT SUM(l.qntDePaginas) FROM lendo l WHERE l.usuarioId.id = :userId AND l.livroId.id = :livroId")
    Double somaPaginasTotaisPorLivroEUsuario(Long userId, Long livroId);

    @Query("SELECT SUM(l.minutos) FROM lendo l WHERE l.usuarioId.id = :userId AND l.livroId.id = :livroId")
    Integer somaMinutosTotaisPorLivroEUsuario(Long userId, Long livroId);

    @Query("SELECT l.dataInicioDeLeitura FROM lendo l WHERE l.id = (SELECT MAX(l2.id) FROM lendo l2 WHERE l2.usuarioId.id = :userId AND l2.livroId.id = :livroId)")
    Date findMaxDataInicioDeLeituraByUsuarioIdAndLivroId(Long userId, Long livroId);

    @Query("SELECT l.dataInicioDeLeitura FROM lendo l WHERE l.id = (SELECT MIN(l2.id) FROM lendo l2 WHERE l2.usuarioId.id = :userId AND l2.livroId.id = :livroId)")
    Date findMinDataInicioDeLeituraByUsuarioIdAndLivroId(Long userId, Long livroId);


}
