package com.example.demo.repositories;

import com.example.demo.entities.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favoritos, Long> {

    List<Favoritos> findByUsuarioId(Long id);

    List<Favoritos> findByLivroId(Long id);
}
