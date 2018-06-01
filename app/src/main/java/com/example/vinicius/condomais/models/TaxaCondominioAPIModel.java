package com.example.vinicius.condomais.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaxaCondominioAPIModel {

    @SerializedName("id") private long id;
    @SerializedName("mes_ano") private String mesAno;
    @SerializedName("data_vencimento") private String dataVencimento;
    @SerializedName("valor_pago") private float valorPago;
    @SerializedName("valor_a_pagar") private float valorAPagar;
    @SerializedName("unidade_habitacional") private long unidadeHabitacional;
    @SerializedName("pago") private boolean pago;
    @SerializedName("itens") private List<ItemTaxaCondominioAPIModel> itens;


    public TaxaCondominioAPIModel(String mesAno, String dataVencimento, long unidadeHabitacional) {
        this.mesAno = mesAno;
        this.dataVencimento = dataVencimento;
        this.unidadeHabitacional = unidadeHabitacional;
    }

    public String getMesAno() {
        return mesAno;
    }

    public boolean isPago() {
        return pago;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public float getValorAPagar() {
        return valorAPagar;
    }

    public List<ItemTaxaCondominioAPIModel> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "TaxaCondominio{ " +
                "mesAno: " + mesAno + '\'' +
                ", valorAPagar: " + valorAPagar +
                ", pago: " + pago +
                '}';
    }

    public long getId() {
        return id;
    }
}
