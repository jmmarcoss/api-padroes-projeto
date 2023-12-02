package com.example.demo.controllers;

import com.example.demo.entities.Finalizado;
import com.example.demo.entities.Lendo;
import com.example.demo.records.finalizado.FinalizadoEntrada;
import com.example.demo.records.finalizado.FinalizadoSaida;
import com.example.demo.records.lendo.LendoEntrada;
import com.example.demo.records.lendo.LendoSaida;
import com.example.demo.services.LendoService;
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
@RequestMapping("/lendo")
public class LendoController {

    @Autowired
    private LendoService lendoService;

    @GetMapping
    public ResponseEntity<List<LendoSaida>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(lendoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LendoSaida> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(lendoService.findById(id));
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<LendoSaida>> findByUsuarioId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(lendoService.findAllPerUsuarioId(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Lendo> insert(@RequestBody LendoEntrada favorito, UriComponentsBuilder uriBuilder){
        var novoLendo = lendoService.insert(favorito);
        var uri = uriBuilder.path("/finalizado/{id}").buildAndExpand(novoLendo.getId()).toUri();
        return ResponseEntity.created(uri).body(novoLendo);
    }
}
