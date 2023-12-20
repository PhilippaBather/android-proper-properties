package com.philippabather.properproperties.api;

import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PropertyApiInterface {

    @Headers({"Accept: application/json"})
    @GET("/properties/rental")
    Call<Set<RentalProperty>> getRentalProperties();
    @Headers({"Accept: application/json"})
    @GET("/properties/rental/{rentalId}")
    Call<RentalProperty> getRentalPropertyById(@Path(value = "rentalId") long rentalId);

    @Headers({"Accept: application/json"})
    @GET("/properties/sale")
    Call<Set<SaleProperty>> getSaleProperties();

    @Headers({"Accept: application/json"})
    @GET("/properties/sale/{saleId}")
    Call<SaleProperty> getSalePropertyById(@Path(value = "saleId") long saleId);

}
