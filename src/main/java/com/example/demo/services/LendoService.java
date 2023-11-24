package com.example.demo.services;

import com.example.demo.entities.Lendo;
import com.example.demo.repositories.LendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LendoService {

    @Autowired
    private LendoRepository lendoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;

    public List<Lendo> findAll(){
        return this.lendoRepository.findAll();
    }

    public Lendo findById(Long id){
        return this.lendoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Lendo> findAllPerUsuarioId(Long id){
        return this.lendoRepository.findByUsuarioId(id);
    }

}
