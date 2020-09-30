package com.igor.coursemanager.network.service;

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Network {

    public static final String BASE_URL = "https://api.vetrf.ru/platform/services/2.0/";
    private static Network mInstance;
    private final Retrofit mRetrofit;

    @SuppressWarnings("deprecation")
    private Network() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
                .build();
    }

    public static Network getInstance() {
        if (mInstance == null) {
            mInstance = new Network();
        }
        return mInstance;
    }

    public MercuryApi createMercuryApi() {
        return mRetrofit.create(MercuryApi.class);
    }
}
