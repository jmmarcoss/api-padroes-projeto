package com.example.demo.services;

import com.example.demo.builders.FinalizadoBuilder;
import com.example.demo.entities.Finalizado;
import com.example.demo.infra.exceptions.finalizado.FinalizadoJaExiste;
import com.example.demo.records.finalizado.FinalizadoEntrada;
import com.example.demo.records.finalizado.FinalizadoSaida;
import com.example.demo.repositories.FinalizadoRepository;
import com.example.demo.repositories.LendoRepository;
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
    @Autowired
    private LendoRepository lendoRepository;

    public List<FinalizadoSaida> findAll(){
        return this.finalizadoRepository.findAllFin();
    }

    public FinalizadoSaida findById(Long id){
        return this.finalizadoRepository.findByIdFin(id).orElseThrow(RuntimeException::new);
    }

    public List<FinalizadoSaida> findAllPerUsuarioId(Long id){
        var usuario = usuarioService.findById(id);
        return this.finalizadoRepository.findByUsuarioId(usuario.getId());
    }

    public Finalizado insert(FinalizadoEntrada finalizadoEntrada){
        var usuario = this.usuarioService.findById(finalizadoEntrada.usuarioId());
        var livro = this.livroService.findById(finalizadoEntrada.livroId());
        if (this.finalizadoRepository.existirInstanciaDoLivro(livro.getId()).size() != 0){
            throw new FinalizadoJaExiste();
        }
        var novoFinalizado = new FinalizadoBuilder()
                .setUsuarioId(usuario)
                .setLivroId(livro)
                .setDataInicioDeLeitura(this.lendoRepository.findMinDataInicioDeLeituraByUsuarioIdAndLivroId(usuario.getId(), livro.getId()))
                .setDataTerminoDeLeitura(this.lendoRepository.findMaxDataInicioDeLeituraByUsuarioIdAndLivroId(usuario.getId(), livro.getId()))
                .setMinutos(this.lendoRepository.somaMinutosTotaisPorLivroEUsuario(usuario.getId(), livro.getId()))
                .setTempoMedioPorPagina(this.lendoRepository.calcularRazaoPaginasPorMinutos(usuario.getId(), livro.getId()))
                .build();
        return this.finalizadoRepository.save(novoFinalizado);
    }

}
