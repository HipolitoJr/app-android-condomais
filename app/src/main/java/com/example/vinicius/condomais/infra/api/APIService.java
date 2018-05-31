package com.example.vinicius.condomais.infra.api;

import com.example.vinicius.condomais.infra.api.endpoints.TokenEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.UsuarioEndPoint;
import com.example.vinicius.condomais.models.TokenAPIModel;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {


    public static  final String BASE_URL = "http://192.168.0.21:8000/api/v1/";
    public Retrofit retrofit;
    public Interceptor interceptor;

    public TokenEndPoint tokenEndPoint;
    public UsuarioEndPoint usuarioEndPoint;

    public APIService(String token){

        this.interceptor = new InterceptorAPI("token " + token);

        OkHttpClient.Builder builderCliente = new OkHttpClient.Builder();
        builderCliente.interceptors().add(this.interceptor);
        OkHttpClient cliente = builderCliente.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        retrofit = builder.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        tokenEndPoint = retrofit.create(TokenEndPoint.class);
        usuarioEndPoint = retrofit.create(UsuarioEndPoint.class);
    }

}
