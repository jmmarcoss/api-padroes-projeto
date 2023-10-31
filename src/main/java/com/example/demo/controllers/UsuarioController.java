package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.infra.security.TokenService;
import com.example.demo.records.usuario.DadosInsertGetUsuario;
import com.example.demo.records.usuario.DadosParaLogin;
import com.example.demo.records.token.TokenRecord;
import com.example.demo.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @Transactional
    @PostMapping(value = "/cadastro")
    public ResponseEntity<Usuario> insert(@Valid @RequestBody DadosInsertGetUsuario usuario, UriComponentsBuilder uriBuilder){
        var novoUsuario = usuarioService.insert(new Usuario(usuario));
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    @Transactional
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid DadosParaLogin dados){
        try {
            var tokenGerado = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(tokenGerado);
            var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenRecord(token));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody DadosInsertGetUsuario usuario) {
        var novoUsuario = new Usuario(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateUser(id, novoUsuario));
    }

    @Transactional
    @PostMapping(value = "/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioService.findByEmail(email));
    }

}
