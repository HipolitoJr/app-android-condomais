package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("username") private String nomeDeUsuario;
    @SerializedName("password") private String senha;

    public Usuario(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
    }

}
