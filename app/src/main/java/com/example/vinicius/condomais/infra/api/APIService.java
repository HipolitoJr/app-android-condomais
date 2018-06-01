package com.example.vinicius.condomais.infra.api;

import com.example.vinicius.condomais.infra.api.endpoints.CondominioEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.GrupoHabitacionalEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.TaxaCondominioEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.TokenEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.UnidadeHabitacionalEndPoint;
import com.example.vinicius.condomais.infra.api.endpoints.UsuarioEndPoint;
import com.example.vinicius.condomais.models.GrupoHabitacionalAPIModel;
import com.example.vinicius.condomais.models.TokenAPIModel;
import com.example.vinicius.condomais.utils.adapters.UnidadeHabitacionalRVAdapter;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {


    public static  final String BASE_URL = "http://192.168.43.6:8000/api/v1/";
    public Retrofit retrofit;
    public Interceptor interceptor;

    public TokenEndPoint tokenEndPoint;
    public UsuarioEndPoint usuarioEndPoint;
    public CondominioEndPoint condominioEndPoint;
    public GrupoHabitacionalEndPoint grupoHabitacionalEndPoint;
    public UnidadeHabitacionalEndPoint unidadeHabitacionalEndPoint;
    public TaxaCondominioEndPoint taxaCondominioEndPoint;

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
        condominioEndPoint = retrofit.create(CondominioEndPoint.class);
        grupoHabitacionalEndPoint = retrofit.create(GrupoHabitacionalEndPoint.class);
        unidadeHabitacionalEndPoint = retrofit.create(UnidadeHabitacionalEndPoint.class);
        taxaCondominioEndPoint = retrofit.create(TaxaCondominioEndPoint.class);
    }

}
