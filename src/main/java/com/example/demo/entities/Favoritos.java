package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity(name = "favoritos")
public class Favoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;
    @Column(name = "livro_id")
    @ManyToOne
    private Livro livroId;

    public Favoritos(Usuario usuarioId, Livro livroId) {
        this.usuarioId = usuarioId;
        this.livroId = livroId;
    }
}
