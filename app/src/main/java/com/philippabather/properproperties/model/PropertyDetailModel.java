package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyDetailContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailModel implements PropertyDetailContract.Model {

    private final PropertyApiInterface api;

    public PropertyDetailModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void loadSelectedRentalProperty(OnLoadRentalPropertyListener listener, long rentalId) {
        Call<RentalProperty> callRental = api.getRentalPropertyById(rentalId);

        callRental.enqueue(new Callback<RentalProperty>() {
            @Override
            public void onResponse(@NonNull Call<RentalProperty> call, @NonNull Response<RentalProperty> response) {
                RentalProperty rental = response.body();
                listener.onLoadRentalPropertySuccess(rental);
            }

            @Override
            public void onFailure(@NonNull Call<RentalProperty> call, @NonNull Throwable t) {
                Log.e("getRental", Objects.requireNonNull(t.getMessage()));
                listener.onLoadRentalPropertyError("Server error");
            }
        });
    }

    @Override
    public void loadSelectedSaleProperty(OnLoadSalePropertyListener listener, long saleId) {
        Call<SaleProperty> callSale = api.getSalePropertyById(saleId);

        callSale.enqueue(new Callback<SaleProperty>() {
            @Override
            public void onResponse(@NonNull Call<SaleProperty> call, @NonNull Response<SaleProperty> response) {
                SaleProperty sale = response.body();
                listener.onLoadSalePropertySuccess(sale);
            }

            @Override
            public void onFailure(@NonNull Call<SaleProperty> call, @NonNull Throwable t) {
                Log.e("getSale", Objects.requireNonNull(t.getMessage()));
                listener.onLoadSalePropertyError("Server error");
            }
        });
    }
}
