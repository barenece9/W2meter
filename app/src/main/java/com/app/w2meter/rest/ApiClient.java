package com.app.w2meter.rest;

/**
 * Created by etrans on 24/4/18.
 */


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {

    public static final String BASE_URL="http://18.217.239.157:8766/api/";
    public static final String BASE_URL_eTrans="http://fetest.etranssolutions.com/eTransRestApi/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
