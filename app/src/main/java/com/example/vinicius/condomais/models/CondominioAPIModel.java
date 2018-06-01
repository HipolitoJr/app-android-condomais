package com.example.vinicius.condomais.models;

public class CondominioAPIModel {

    private String nome;
    private String cnpj;
    private String endereco;
    private long sindico;

    public CondominioAPIModel(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void setSindico(long sindico) {
        this.sindico = sindico;
    }
}
