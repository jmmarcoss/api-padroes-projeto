package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario insert(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) { return usuarioRepository.findByEmail(email); }

    @Transactional
    public Usuario updateUser(Long id, String nome) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(nome);
                    return usuarioRepository.save(usuario);
                }).orElseThrow(RuntimeException::new);
    }
}
