package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("username") private String nomeDeUsuario;
    @SerializedName("email") private String email;
    @SerializedName("password") private String senha;

    public Usuario(String nomeDeUsuario, String email, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.senha = senha;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }
}
