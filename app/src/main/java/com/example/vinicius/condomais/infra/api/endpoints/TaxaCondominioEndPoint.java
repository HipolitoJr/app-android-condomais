package com.example.vinicius.condomais.infra.api.endpoints;

import com.example.vinicius.condomais.models.TaxaCondominioAPIModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TaxaCondominioEndPoint {

    @GET("taxacondominio/{id}/")
    Call<TaxaCondominioAPIModel> getTaxaCondominioUnica(@Path("id") long id);

}
