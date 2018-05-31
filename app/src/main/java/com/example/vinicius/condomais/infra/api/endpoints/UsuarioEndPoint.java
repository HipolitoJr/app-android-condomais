package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioEndPoint {

    @POST("usuario/")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

}
