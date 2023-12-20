package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyListModel implements PropertyListContract.Model {

    private final PropertyApiInterface api;

    public PropertyListModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void loadRentalProperties(OnLoadPropertiesListener listener) {
        Call<Set<RentalProperty>> callProperties = api.getRentalProperties();

        callProperties.enqueue(new Callback<Set<RentalProperty>>() {
            @Override
            public void onResponse(@NonNull Call<Set<RentalProperty>> call, Response<Set<RentalProperty>> response) {
                Set<RentalProperty> properties = response.body();
                List<RentalProperty> rentalPropertyList = new ArrayList<>(properties);
                for (RentalProperty rentalProperty :
                        rentalPropertyList) {
                    Log.e("rental-property", rentalProperty.toString());
                }
                listener.onLoadRentalPropertiesSuccess(rentalPropertyList);
            }

            @Override
            public void onFailure(Call<Set<RentalProperty>> call, Throwable t) {
                Log.e("getProperties", Objects.requireNonNull(t.getMessage()));
                listener.onLoadPropertiesError("Se ha producido un error al connectar con el servidor.");
            }
        });
    }

    @Override
    public void loadSaleProperties(OnLoadPropertiesListener listener) {
        Call<Set<SaleProperty>> callProperties = api.getSaleProperties();

        callProperties.enqueue(new Callback<Set<SaleProperty>>() {
            @Override
            public void onResponse(Call<Set<SaleProperty>> call, Response<Set<SaleProperty>> response) {
                Set<SaleProperty> properties = response.body();
                List<SaleProperty> salePropertyList = new ArrayList<>(properties);
                for (SaleProperty rentalProperty :
                        salePropertyList) {
                    Log.e("sale-property", rentalProperty.toString());
                }
                listener.onLoadSalePropertiesSuccess(salePropertyList);
            }

            @Override
            public void onFailure(Call<Set<SaleProperty>> call, Throwable t) {

            }
        });
    }
}
