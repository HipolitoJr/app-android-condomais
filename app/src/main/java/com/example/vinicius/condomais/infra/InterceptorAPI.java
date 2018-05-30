package com.example.vinicius.condomais.infra;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class InterceptorAPI implements Interceptor{

    public static final String AUTHORIZATION = "Authorization";
    public final String TOKEN;

    public InterceptorAPI(String token) {
        this.TOKEN = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(AUTHORIZATION, TOKEN)
                .build();

        return chain.proceed(request);
    }

}
