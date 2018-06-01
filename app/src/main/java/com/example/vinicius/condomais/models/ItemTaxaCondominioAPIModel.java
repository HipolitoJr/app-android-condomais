package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

public class ItemTaxaCondominioAPIModel {

    @SerializedName("id") private long id;
    @SerializedName("descricao") private String descricao;
    @SerializedName("valor") private float valor;
    @SerializedName("taxa_condominio") private long taxaCondominio;

    public ItemTaxaCondominioAPIModel(String descricao, float valor, long taxaCondominio) {
        this.descricao = descricao;
        this.valor = valor;
        this.taxaCondominio = taxaCondominio;
    }

    public long getId() {
        return id;
    }

    public float getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
