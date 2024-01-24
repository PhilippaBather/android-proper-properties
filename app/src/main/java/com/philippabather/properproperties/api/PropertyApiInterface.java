package com.philippabather.properproperties.api;

import com.philippabather.properproperties.domain.LoginRequest;
import com.philippabather.properproperties.domain.LoginResponse;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * PropertyApiInterface - contiene los mappings para GET, POST, PUT y DELETE requests.
 *
 * @author Philippa Bather
 */
public interface PropertyApiInterface {

    // unsecured endpoints - general user view
    @Headers({"Accept: application/json"})
    @GET("/properties/rental")
    Call<Set<RentalProperty>> getRentalProperties();

    @Headers({"Accept: application/json"})
    @GET("/properties/sale")
    Call<Set<SaleProperty>> getSaleProperties();

    @Headers({"Accept: application/json"})
    @GET("/properties/rental/{rentalId}")
    Call<RentalProperty> getRentalPropertyById(@Path(value = "rentalId") long rentalId);

    @Headers({"Accept: application/json"})
    @GET("/properties/sale/{saleId}")
    Call<SaleProperty> getSalePropertyById(@Path(value = "saleId") long saleId);


    // secured endpoints - proprietor portal

    @Headers({"Accept: application/json"})
    @POST("/token")
    Call<LoginResponse> authenticateUser(@Body LoginRequest loginRequestDetails);

    @Headers({"Accept: application/json"})
    @GET("/users/proprietors/secured/{username}")
    Call<Proprietor> getProprietorByUsername(@Header( "Authorization") String token, @Path(value = "username") String username);

    @Headers({"Accept: application/json"})
    @POST("/properties/rental/{proprietorId}")
    Call<RentalProperty> saveRentalProperty(@Header("Authorization") String token, @Path(value = "proprietorId") long proprietorId, @Body RentalProperty rentalProperty);

    @Headers({"Accept: application/json"})
    @POST("/properties/sale/{proprietorId}")
    Call<SaleProperty> saveSaleProperty(@Header("Authorization") String token, @Path(value = "proprietorId") long proprietorId, @Body SaleProperty saleProperty);

    @Headers({"Accept: application/json"})
    @DELETE("/properties/rental/{propertyId}")
    Call<Void> deleteRentalPropertyById(@Header("Authorization") String token, @Path(value = "propertyId") long propertyId);

    @Headers({"Accept: application/json"})
    @DELETE("properties/sale/{propertyId}")
    Call<Void> deleteSalePropertyById(@Header("Authorization") String token, @Path(value = "propertyId") long propertyId);

    @Headers({"Accept: application/json"})
    @PUT("/properties/rental/{propertyId}")
    Call<RentalProperty> updateRentalPropertyById(@Header("Authorization") String token, @Path(value = "propertyId") long propertyId, @Body RentalProperty rentalProperty);

    @Headers({"Accept: application/json"})
    @PUT("/properties/sale/{propertyId}")
    Call<SaleProperty> updateSalePropertyById(@Header("Authorization") String token, @Path(value = "propertyId") long propertyId, @Body SaleProperty saleProperty);

}
