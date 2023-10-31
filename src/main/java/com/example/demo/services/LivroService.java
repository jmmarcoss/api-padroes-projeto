package com.example.demo.services;

import com.example.demo.entities.Livro;
import com.example.demo.entities.Usuario;
import com.example.demo.infra.exceptions.usuario.EmailJaExistente;
import com.example.demo.infra.exceptions.usuario.NomeJaExistente;
import com.example.demo.infra.exceptions.usuario.UsuarioNaoEncontrado;
import com.example.demo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll(){
        return livroRepository.findAll();
    }

    public List<Livro> findByAutor(String autor){
        return livroRepository.findByAutor(autor);
    }

    public Livro findById(Long id){
        return livroRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id){
        if (!livroRepository.existsById(id)){
            throw new RuntimeException();
        }
        livroRepository.deleteById(id);
    }

//    public Livro insert(Livro livro){
//        if (this.naoExisteEsteEmailENome(usuario)){
//            return usuarioRepository.save(usuario);
//        }
//        return null;
//    }
//
//    public Usuario findByEmail(String email) { return usuarioRepository.findByEmail(email); }
//
//
//    public Usuario updateUser(Long id, Usuario usuario) {
//        if (this.naoExisteEsteEmailENome(usuario)) {
//            return usuarioRepository.findById(id)
//                    .map(novo -> {
//                        novo.setNome(usuario.getNome());
//                        novo.setSenha(usuario.getSenha());
//                        return usuarioRepository.save(novo);
//                    }).orElseThrow(RuntimeException::new);
//        }
//        return null;
//    }
//
//    private boolean naoExisteEsteEmailENome(Usuario usuario){
//        if (usuarioRepository.existsByEmail(usuario.getEmail())){
//            throw new EmailJaExistente();
//        } else if (usuarioRepository.existsByNome(usuario.getNome())){
//            throw new NomeJaExistente();
//        }
//        return true;
//    }
}
