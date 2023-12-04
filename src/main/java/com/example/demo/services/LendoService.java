package com.example.demo.services;

import com.example.demo.builders.LendoBuilder;
import com.example.demo.entities.Lendo;
import com.example.demo.records.lendo.LendoEntrada;
import com.example.demo.records.lendo.LendoSaida;
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

    public List<LendoSaida> findAll(){
        return this.lendoRepository.findAllLen();
    }

    public LendoSaida findById(Long id){
        return this.lendoRepository.findByIdLen(id).orElseThrow(RuntimeException::new);
    }

    public List<LendoSaida> findAllPerUsuarioId(Long id){
        return this.lendoRepository.findByUsuarioId(id);
    }

    public Lendo insert(LendoEntrada  lendoEntrada){
        var usuario = this.usuarioService.findById(lendoEntrada.usuarioId());
        var livro = this.livroService.findById(lendoEntrada.livroId());
        var novoLendo = new LendoBuilder()
                .setUsuarioId(usuario)
                .setLivroId(livro)
                .setDataInicioDeLeitura(lendoEntrada.dataInicioDeLeitura())
                .setDataTerminoDeLeitura(lendoEntrada.dataTerminoDeLietura())
                .setMinutos(lendoEntrada.minutos())
                .setTempoMedioPorPagina(lendoEntrada.tempoMedioPorPagina())
                .setPorcentagemLida(lendoEntrada.porcentagemLida())
                .setQtdDePaginas(lendoEntrada.qntDePaginas())
                .build();
        var lendoSalvar  = this.lendoRepository.save(novoLendo);
        var minutosTotais = this.lendoRepository.somaMinutosTotais(usuario.getId());
        var paginasTotais = this.lendoRepository.somaPaginasTotais(usuario.getId());
        usuario.setTempoTotalDeLeitura(minutosTotais);
        usuario.setTempoMedioPorPagina(paginasTotais/minutosTotais);
        this.usuarioService.insert(usuario);
        return lendoSalvar;
    }

}
