package org.pursuit.unit_03_assessment.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRetrofit {
    private Retrofit retrofitInstance;

    public myRetrofit() {}

    public Retrofit getInstance() {
        if (retrofitInstance != null) {
            return retrofitInstance;
        }
        retrofitInstance = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitInstance;
    }
}
