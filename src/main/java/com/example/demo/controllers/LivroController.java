package com.example.demo.controllers;

import com.example.demo.entities.Livro;
import com.example.demo.services.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findById(id));
    }

}
