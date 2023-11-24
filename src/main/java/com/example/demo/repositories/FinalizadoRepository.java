package com.example.demo.repositories;

import com.example.demo.entities.Finalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalizadoRepository extends JpaRepository<Finalizado, Long> {

    List<Finalizado> findByUsuarioId(Long id);
}
