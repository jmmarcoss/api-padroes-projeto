package com.example.demo.repositories;

import com.example.demo.entities.Livro;
import com.example.demo.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM livros l WHERE LOWER(l.titulo) LIKE %:titulo%")
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByAutor(String autor);

    @Query("SELECT l FROM livros l WHERE LOWER(l.autor) LIKE %:autor%")
    List<Livro> existsByAutor(String autor);

    @Query("SELECT l FROM livros l WHERE LOWER(l.titulo) LIKE %:titulo%")
    List<Livro> existsByTitulo(String titulo);

    @Query("SELECT l FROM livros l WHERE l.categoria = :categoria")
    List<Livro> findByCategoria(Categoria categoria);

}
