package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyRegistrationContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * PropertyRegistrationModel - model para manejar comunicación con el base de datos para registrar
 * objetos de inmuebles.
 *
 * @author Philippa Bather
 */
public class PropertyRegistrationModel implements PropertyRegistrationContract.Model {

    private final PropertyApiInterface api;

    public PropertyRegistrationModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void createNewRentalProperty(OnRegisterPropertyListener listener, String token, long proprietorId, RentalProperty rental) {
        Call<RentalProperty> newRental = api.saveRentalProperty(token, proprietorId, rental);

        newRental.enqueue(new Callback<RentalProperty>() {
            @Override
            public void onResponse(@NonNull Call<RentalProperty> call, @NonNull Response<RentalProperty> response) {
                RentalProperty rental = response.body();
                Log.e("rental", rental.toString());
                listener.onRegisterRentalPropertySuccess(rental);
            }

            @Override
            public void onFailure(@NonNull Call<RentalProperty> call, @NonNull Throwable t) {
                Log.e("addRentalProperty", Objects.requireNonNull(t.getMessage()));
                listener.onRegisterPropertyError(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void createNewSaleProperty(OnRegisterPropertyListener listener, String token, long proprietorId, SaleProperty sale) {
        Call<SaleProperty> newSale = api.saveSaleProperty(token, proprietorId, sale);

        newSale.enqueue(new Callback<SaleProperty>() {
            @Override
            public void onResponse(@NonNull Call<SaleProperty> call, @NonNull Response<SaleProperty> response) {
                SaleProperty sale = response.body();
                 listener.onRegisterSalePropertySuccess(sale);
            }

            @Override
            public void onFailure(@NonNull Call<SaleProperty> call, @NonNull Throwable t) {
                Log.e("addSaleProperty", Objects.requireNonNull(t.getMessage()));
                listener.onRegisterPropertyError(t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
