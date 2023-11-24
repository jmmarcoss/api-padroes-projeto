package com.example.demo.repositories;

import com.example.demo.entities.Lendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendoRepository extends JpaRepository<Lendo, Long> {

    List<Lendo> findByUsuarioId(Long id);
}
