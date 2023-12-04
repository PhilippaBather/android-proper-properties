package com.philippabather.properproperties.api;

import com.philippabather.properproperties.domain.Property;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PropertyApiInterface {

    @Headers({"Accept: application/json"})
    @GET("/properties")
    Call<Set<Property>> getProperties();
}
