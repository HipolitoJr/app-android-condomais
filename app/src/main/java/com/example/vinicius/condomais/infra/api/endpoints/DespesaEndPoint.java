package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.DespesaAPIModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DespesaEndPoint {

    @POST("despesa/unidadehabitacional/{id}/")
    Call<DespesaAPIModel> registrarDespesaUnidadeHabitacional(@Body DespesaAPIModel despesaAPIModel);

}
