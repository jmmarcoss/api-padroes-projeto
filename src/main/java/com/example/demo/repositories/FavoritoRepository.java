package com.example.demo.repositories;

import com.example.demo.entities.Favorito;
import com.example.demo.records.favorito.FavoritoSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query("SELECT NEW com.example.demo.records.favorito.FavoritoSaida(f.id, f.livroId) FROM favoritos f")
    List<FavoritoSaida> findAllFav();

    @Query("SELECT NEW com.example.demo.records.favorito.FavoritoSaida(f.id, f.livroId) FROM favoritos f WHERE f.id = :id")
    Optional<FavoritoSaida> findByIdFav(Long id);

    @Query("SELECT NEW com.example.demo.records.favorito.FavoritoSaida(f.id, f.livroId) FROM favoritos f JOIN f.usuarioId u WHERE u.id = :id")
    List<FavoritoSaida> findByUsuarioId(Long id);

    @Query("SELECT f FROM favoritos f JOIN f.livroId u WHERE u.id = :id")
    List<Favorito> findByLivroId(Long id);
}
