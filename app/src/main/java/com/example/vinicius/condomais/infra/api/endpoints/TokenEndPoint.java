package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.TokenAPIModel;
import com.example.vinicius.condomais.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TokenEndPoint {

    @POST("token/")
    Call<TokenAPIModel> login(@Body Usuario usuario);

}
