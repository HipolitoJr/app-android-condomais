package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GrupoHabitacionalAPIModel {

    @SerializedName("id") private long id;
    @SerializedName("descricao") private String descricao;
    @SerializedName("qtd_unidades") private int qtdUnidades;
    @SerializedName("condominio") private long condominio;
    @SerializedName("unidades_habitacionais") private List<UnidadeHabitacionalAPIModel> unidades;

    public GrupoHabitacionalAPIModel(String descricao, int qtdUnidades, long condominio) {
        this.descricao = descricao;
        this.qtdUnidades = qtdUnidades;
        this.condominio = condominio;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getId() {
        return id;
    }

    public List<UnidadeHabitacionalAPIModel> getUnidades() {
        return unidades;
    }
}
