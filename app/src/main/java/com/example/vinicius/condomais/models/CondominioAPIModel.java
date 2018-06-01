package com.example.vinicius.condomais.models;

public class CondominioAPIModel {

    private String nome;
    private String cnpj;
    private String endereco;

    public CondominioAPIModel(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
}
