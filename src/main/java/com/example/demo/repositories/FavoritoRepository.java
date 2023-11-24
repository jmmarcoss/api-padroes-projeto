package com.example.demo.repositories;

import com.example.demo.entities.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query("SELECT f FROM favoritos f JOIN f.usuarioId u WHERE u.id = :id")
    List<Favorito> findByUsuarioId(Long id);

    @Query("SELECT f FROM favoritos f JOIN f.livroId u WHERE u.id = :id")
    List<Favorito> findByLivroId(Long id);
}
