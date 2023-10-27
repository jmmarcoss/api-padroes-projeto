package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.infra.exceptions.EmailJaExistente;
import com.example.demo.infra.exceptions.NomeJaExistente;
import com.example.demo.infra.exceptions.UsuarioNaoEncontrado;
import com.example.demo.records.usuario.DadosInsertGetUsuario;
import com.example.demo.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(UsuarioNaoEncontrado::new);
    }

    public void deleteById(Long id){
        if (!usuarioRepository.existsById(id)){
            throw new UsuarioNaoEncontrado();
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario insert(Usuario usuario){
        if (this.naoExisteEsteEmailENome(usuario)){
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public Usuario findByEmail(String email) { return usuarioRepository.findByEmail(email); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepository.findByEmail(username);
    }

    public Usuario updateUser(Long id, Usuario usuario) {
        if (this.naoExisteEsteEmailENome(usuario)) {
            return usuarioRepository.findById(id)
                    .map(novo -> {
                        novo.setNome(usuario.getNome());
                        novo.setSenha(usuario.getSenha());
                        return usuarioRepository.save(novo);
                    }).orElseThrow(RuntimeException::new);
        }
       return null;
    }

    private boolean naoExisteEsteEmailENome(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailJaExistente();
        } else if (usuarioRepository.existsByNome(usuario.getNome())){
            throw new NomeJaExistente();
        }
        return true;
    }

}
