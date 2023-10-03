package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder){
        var novoUsuario = usuarioService.insert(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateUser(id, usuario));
    }

    @PostMapping(value = "/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioService.findByEmail(email));
    }

}
