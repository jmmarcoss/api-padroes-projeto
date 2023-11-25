package com.example.demo.repositories;

import com.example.demo.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByTitulo(String titulo);

    List<Livro> findByAutor(String autor);

    boolean existsByAutor(String autor);

    boolean existsByTitulo(String titulo);

    List<Livro> findByCategoria(String categoria);

}
