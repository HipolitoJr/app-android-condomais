package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnidadeHabitacionalAPIModel {

    @SerializedName("id") private long id;
    @SerializedName("descricao") private String descricao;
    @SerializedName("qtd_quartos") private long qtdQuartos;
    @SerializedName("ocupacao") private String ocupacao;
    @SerializedName("proprietario") private long proprietario;
    @SerializedName("grupo_habitacional") private long grupoHabitacional;
    @SerializedName("minhas_taxas") private List<TaxaCondominioAPIModel> taxas;

    public UnidadeHabitacionalAPIModel(String descricao, long qtdQuartos, String ocupacao, long proprietario, long grupoHabitacional) {
        this.descricao = descricao;
        this.qtdQuartos = qtdQuartos;
        this.ocupacao = ocupacao;
        this.proprietario = proprietario;
        this.grupoHabitacional = grupoHabitacional;
    }

    public List<TaxaCondominioAPIModel> getTaxas() {
        return taxas;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getId() {
        return id;
    }
}
