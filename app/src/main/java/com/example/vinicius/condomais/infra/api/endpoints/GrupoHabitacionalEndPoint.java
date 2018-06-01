package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.GrupoHabitacionalAPIModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GrupoHabitacionalEndPoint {

    @GET("grupohabitacional/{id}/")
    Call<GrupoHabitacionalAPIModel> getGrupoHabitacionalUnico(@Path("id") long id);

    @GET("grupohabitacional/")
    Call<List<GrupoHabitacionalAPIModel>> getGruposHabitacionais();

    @POST("grupohabitacional/")
    Call<GrupoHabitacionalAPIModel> registrarGrupoHabitacional(@Body GrupoHabitacionalAPIModel grupoHabitacionalAPIModel);

}
