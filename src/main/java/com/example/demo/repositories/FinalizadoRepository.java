package com.example.demo.repositories;

import com.example.demo.entities.Finalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalizadoRepository extends JpaRepository<Finalizado, Long> {

    @Query("SELECT f FROM finalizados f JOIN f.usuarioId u WHERE u.id = :id")
    List<Finalizado> findByUsuarioId(Long id);
}
