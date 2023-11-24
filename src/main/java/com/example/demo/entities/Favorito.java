package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livroId;

    public Favorito(Usuario usuarioId, Livro livroId) {
        this.usuarioId = usuarioId;
        this.livroId = livroId;
    }
}
