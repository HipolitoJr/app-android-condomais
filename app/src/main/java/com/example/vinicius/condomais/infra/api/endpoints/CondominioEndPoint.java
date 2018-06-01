package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.CondominioAPIModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CondominioEndPoint {

    @POST("condominio/")
    Call<CondominioAPIModel> registrarCondominio(@Body CondominioAPIModel condominioAPIModel);

}
