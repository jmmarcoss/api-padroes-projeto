package com.example.demo.controllers;

import com.example.demo.entities.Favorito;
import com.example.demo.entities.Finalizado;
import com.example.demo.records.favorito.FavoritoEntrada;
import com.example.demo.records.favorito.FavoritoSaida;
import com.example.demo.records.finalizado.FinalizadoEntrada;
import com.example.demo.records.finalizado.FinalizadoSaida;
import com.example.demo.services.FinalizadoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/finalizado")
public class FinalizadoController {

    @Autowired
    private FinalizadoService finalizadoService;

    @GetMapping
    public ResponseEntity<List<FinalizadoSaida>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(finalizadoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FinalizadoSaida> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(finalizadoService.findById(id));
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<FinalizadoSaida>> findByUsuarioId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(finalizadoService.findAllPerUsuarioId(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Finalizado> insert(@RequestBody FinalizadoEntrada favorito, UriComponentsBuilder uriBuilder){
        var novoFinalizado = finalizadoService.insert(favorito);
        var uri = uriBuilder.path("/finalizado/{id}").buildAndExpand(novoFinalizado.getId()).toUri();
        return ResponseEntity.created(uri).body(novoFinalizado);
    }

}
