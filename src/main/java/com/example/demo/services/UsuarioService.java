package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.infra.exceptions.usuario.EmailJaExistente;
import com.example.demo.infra.exceptions.usuario.NomeJaExistente;
import com.example.demo.infra.exceptions.usuario.UsuarioNaoEncontrado;
import com.example.demo.infra.security.Criptografia;
import com.example.demo.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    @Autowired
    private Criptografia passwordEncoder;

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
        var senhaCriptografada = passwordEncoder.encoderPassword(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        if (this.naoExisteEsteEmail(usuario)){
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public Usuario findByEmail(String email) {
        if (!usuarioRepository.existsByEmail(email)){
            throw new UsuarioNaoEncontrado();
        }
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepository.findByEmail(username);
    }

    public Usuario updateUser(Long id, Usuario usuario) {
        if (this.naoExisteEsteEmail(usuario)) {
            return usuarioRepository.findById(id)
                    .map(novo -> {
                        novo.setNome(usuario.getNome());
                        novo.setSenha(usuario.getSenha());
                        return usuarioRepository.save(novo);
                    }).orElseThrow(RuntimeException::new);
        }
       return null;
    }

    private boolean naoExisteEsteEmail(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailJaExistente();
        }
        return true;
    }

}
