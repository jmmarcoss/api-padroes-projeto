package com.example.demo.services;

import com.example.demo.builders.LendoBuilder;
import com.example.demo.entities.Lendo;
import com.example.demo.records.lendo.LendoEntrada;
import com.example.demo.records.lendo.LendoSaida;
import com.example.demo.repositories.LendoRepository;
import com.example.demo.repositories.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroService livroService;

    public List<LendoSaida> findAll(){
        return this.lendoRepository.findAllLen();
    }

    public LendoSaida findById(Long id){
        return this.lendoRepository.findByIdLen(id).orElseThrow(RuntimeException::new);
    }

    public List<LendoSaida> findAllPerUsuarioId(Long id){
        var usuario = usuarioService.findById(id);
        return this.lendoRepository.findByUsuarioId(usuario.getId());
    }

    public Lendo insert(LendoEntrada  lendoEntrada){
        var usuario = this.usuarioService.findById(lendoEntrada.usuarioId());
        var livro = this.livroService.findById(lendoEntrada.livroId());
        var novoLendo = new LendoBuilder()
                .setUsuarioId(usuario)
                .setLivroId(livro)
                .setDataInicioDeLeitura(lendoEntrada.dataInicioDeLeitura())
                .setDataTerminoDeLeitura(lendoEntrada.dataTerminoDeLeitura())
                .setMinutos((int) lendoEntrada.minutos())
                .setTempoMedioPorPagina(lendoEntrada.qntDePaginas() / lendoEntrada.minutos())
                .setPorcentagemLida(null)
                .setQtdDePaginas((int) lendoEntrada.qntDePaginas())
                .build();
        var lendoSalvar  = this.lendoRepository.save(novoLendo);
        if (lendoSalvar.getPorcentagemLida() == null){
            lendoSalvar.setPorcentagemLida((this.lendoRepository.somaPaginasTotaisPorLivroEUsuario(usuario.getId(), livro.getId()))/livro.getPaginas());
            lendoSalvar  = this.lendoRepository.save(lendoSalvar);
        }
        var minutosTotais = Double.valueOf(this.lendoRepository.somaMinutosTotais(usuario.getId()));
        var paginasTotais = Double.valueOf(this.lendoRepository.somaPaginasTotais(usuario.getId()));
        usuario.setTempoTotalDeLeitura(minutosTotais);
        usuario.setTempoMedioPorPagina(minutosTotais/paginasTotais);
        this.usuarioRepository.save(usuario);
        return lendoSalvar;
    }

}
