package com.example.demo.entities;

import com.example.demo.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @NotNull
    private int paginas;
    @NotBlank
    @Column(name = "data_de_publicacao")
    private LocalDateTime dataDePublicacao;
    @NotBlank
    @Column(name = "url_img")
    private String urlImg;


}