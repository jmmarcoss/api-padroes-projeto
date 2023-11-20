package com.example.demo.controllers;

import com.example.demo.entities.Favoritos;
import com.example.demo.entities.Usuario;
import com.example.demo.records.favorito.FavoritoEntrada;
import com.example.demo.records.usuario.DadosInsertGetUsuario;
import com.example.demo.services.FavoritoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/favorito")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;


    @GetMapping
    public ResponseEntity<List<Favoritos>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(favoritoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Favoritos> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(favoritoService.findById(id));
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<Favoritos>> findByUsuarioId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(favoritoService.findAllPerUsuario(id));
    }

    @GetMapping(value = "/livro/{id}")
    public ResponseEntity<List<Favoritos>> findByLivroId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(favoritoService.findAllPerLivro(id));
    }

    @Transactional
    @PostMapping(value = "/favoritar")
    public ResponseEntity<Favoritos> insert(@RequestBody FavoritoEntrada favorito, UriComponentsBuilder uriBuilder){
        var novoFavorito = favoritoService.insert(favorito);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(novoFavorito.getId()).toUri();
        return ResponseEntity.created(uri).body(novoFavorito);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        favoritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
