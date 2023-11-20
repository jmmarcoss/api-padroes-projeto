package com.example.demo.services;

import com.example.demo.entities.Favoritos;
import com.example.demo.infra.exceptions.favorito.FavoritoNaoExistente;
import com.example.demo.records.favorito.FavoritoEntrada;
import com.example.demo.repositories.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;


    public List<Favoritos> findAll(){
        return favoritoRepository.findAll();
    }

    public Favoritos findById(Long id){
        return favoritoRepository.findById(id).orElseThrow(FavoritoNaoExistente::new);
    }

    public List<Favoritos> findAllPerUsuario(Long id){
        return favoritoRepository.findByUsuarioId(id);
    }

    public List<Favoritos> findAllPerLivro(Long id){
        return favoritoRepository.findByLivroId(id);
    }

    public Favoritos insert(FavoritoEntrada favorito){
        var usuario = usuarioService.findById(favorito.usuarioId());
        var livro = livroService.findById(favorito.livroId());
        var favoritoNovo  = new Favoritos(usuario, livro);
        return favoritoRepository.save(favoritoNovo);
    }

    public void deleteById(Long id){
        if(!this.favoritoRepository.existsById(id)){
            throw new FavoritoNaoExistente();
        }
        this.favoritoRepository.deleteById(id);
    }
}
