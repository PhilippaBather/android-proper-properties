package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyUpdateContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyUpdateModel implements PropertyUpdateContract.Model {

    private final PropertyApiInterface api;

    public PropertyUpdateModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void deleteRentalProperty(OnDeletePropertyListener listener, long propertyId) {
        Call<Void> call = api.deleteRentalPropertyById(propertyId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onDeleteRentalPropertySuccess();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("deleteRentalPropertyFailure", Objects.requireNonNull(t.getMessage()));
                listener.onDeletePropertyError(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void deleteSaleProperty(OnDeletePropertyListener listener, long propertyId) {
        Call<Void> call = api.deleteSalePropertyById(propertyId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onDeleteSalePropertySuccess();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("deleteSalePropertyFailure", Objects.requireNonNull(t.getMessage()));
                listener.onDeletePropertyError(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateRentalProperty(OnUpdatePropertyListener listener, long propertyId, RentalProperty rentalProperty) {
        Call<RentalProperty> callRental = api.updateRentalPropertyById(propertyId, rentalProperty);

        callRental.enqueue(new Callback<RentalProperty>() {
            @Override
            public void onResponse(@NonNull Call<RentalProperty> call, @NonNull Response<RentalProperty> response) {
                RentalProperty rental = response.body();
                listener.onUpdateRentalPropertySuccess(rental);
            }

            @Override
            public void onFailure(@NonNull Call<RentalProperty> call, @NonNull Throwable t) {
                Log.e("rentalPropertyUpdateFailure", Objects.requireNonNull(t.getMessage()));
                listener.onUpdatePropertyError(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateSaleProperty(OnUpdatePropertyListener listener, long propertyId, SaleProperty saleProperty) {
        Call<SaleProperty> callSale = api.updateSalePropertyById(propertyId, saleProperty);

        callSale.enqueue(new Callback<SaleProperty>() {
            @Override
            public void onResponse(@NonNull Call<SaleProperty> call, @NonNull Response<SaleProperty> response) {
                SaleProperty sale = response.body();
                listener.onUpdateSalePropertySuccess(sale);
            }

            @Override
            public void onFailure(@NonNull Call<SaleProperty> call, @NonNull Throwable t) {
                Log.e("salePropertyUpdateFailure", Objects.requireNonNull(t.getMessage()));
                listener.onUpdatePropertyError(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
