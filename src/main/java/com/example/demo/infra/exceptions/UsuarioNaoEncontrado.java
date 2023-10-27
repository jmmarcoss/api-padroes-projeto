package com.example.demo.infra.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado() {
        super("Usuário não encontrado");
    }
}
