package com.philippabather.properproperties.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.OwnerContract;
import com.philippabather.properproperties.domain.Proprietor;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerModel implements OwnerContract.Model {

    private final PropertyApiInterface api;
    public OwnerModel(Context context) {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void loadProprietor(OnLoadProprietorListener listener, long proprietorId) {
        Call<Proprietor> callProprietor = api.getProprietorById(proprietorId);

        callProprietor.enqueue(new Callback<Proprietor>() {
            @Override
            public void onResponse(@NonNull Call<Proprietor> call, @NonNull Response<Proprietor> response) {
                Proprietor proprietor = response.body();
                Log.e("proprietor", proprietor.toString());
                listener.onLoadProprietorSuccess(proprietor);
            }

            @Override
            public void onFailure(@NonNull Call<Proprietor> call, @NonNull Throwable t) {
                Log.e("getProprietor", Objects.requireNonNull(t.getMessage()));
                listener.onLoadProprietorError("Se ha producido un error al connectar con el servidor.");
            }
        });
    }

}
