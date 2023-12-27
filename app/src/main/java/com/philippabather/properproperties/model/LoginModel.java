package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.Proprietor;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    private final PropertyApiInterface api;

    public LoginModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void loadProprietorByUsernameAndPassword(OnLoadProprietorListener listener, String username, String password) {
        Call<Proprietor> callProprietor = api.getProprietorByUsernameAndPassword(username, password);

        callProprietor.enqueue(new Callback<Proprietor>() {
            @Override
            public void onResponse(@NonNull Call<Proprietor> call, @NonNull Response<Proprietor> response) {
                Proprietor proprietor = response.body();
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
