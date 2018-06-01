package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.UnidadeHabitacionalAPIModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UnidadeHabitacionalEndPoint {

    @GET("unidadehabitacional/{id}")
    Call<UnidadeHabitacionalAPIModel> getUnidadeHabitacionalUnica(@Path("id") long id);

}
