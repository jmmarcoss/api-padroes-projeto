package com.example.demo.infra.exceptions.usuario;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado() {
        super("Usuário não encontrado");
    }
}
