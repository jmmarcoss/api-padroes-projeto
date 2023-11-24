package com.example.demo.services;

import com.example.demo.entities.Finalizado;
import com.example.demo.records.finalizado.FinalizadoEntrada;
import com.example.demo.repositories.FavoritoRepository;
import com.example.demo.repositories.FinalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalizadoService {

    @Autowired
    private FinalizadoRepository finalizadoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;

    public List<Finalizado> findAll(){
        return this.finalizadoRepository.findAll();
    }

    public Finalizado findById(Long id){
        return this.finalizadoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Finalizado> findAllPerUsuarioId(Long id){
        return this.finalizadoRepository.findByUsuarioId(id);
    }

    public Finalizado insert(FinalizadoEntrada finalizadoEntrada){
        var usuario = this.usuarioService.findById(finalizadoEntrada.usuarioId());
        var livro = this.livroService.findById(finalizadoEntrada.livroId());
        var novoFinalizado = new Finalizado(usuario, livro, finalizadoEntrada.dataInicioDeLeitura(), finalizadoEntrada.dataTerminoDeLietura()
        ,finalizadoEntrada.minutos(), finalizadoEntrada.tempoMedioPorPagina());
        return this.finalizadoRepository.save(novoFinalizado);
    }

}
