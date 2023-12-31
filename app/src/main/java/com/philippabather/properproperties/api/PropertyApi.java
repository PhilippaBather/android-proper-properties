package com.philippabather.properproperties.api;

import static com.philippabather.properproperties.api.ApiConstants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * PropertyApi - contiene una interfaz para crear una instancia del DB de Retrofit.
 *
 * @author Philippa Bather
 */
public class PropertyApi {

    public static PropertyApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(PropertyApiInterface.class);
    }
}
