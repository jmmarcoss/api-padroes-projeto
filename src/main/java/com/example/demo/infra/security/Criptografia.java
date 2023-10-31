package com.example.demo.infra.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Criptografia {

    private static Criptografia instance;

    private Criptografia(){}

    public static Criptografia getInstance(){
        if (instance == null){
            instance = new Criptografia();
        }
        return instance;
    }

    public String encoderPassword(String senha){
        return BCrypt.withDefaults().hashToString(6, senha.toCharArray());
    }
}
