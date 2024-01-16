package com.philippabather.properproperties.model;

import static com.philippabather.properproperties.constants.Constants.SERVER_ERROR;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.ProprietorContract;
import com.philippabather.properproperties.domain.Proprietor;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProprietorModel implements ProprietorContract.Model {

    private final PropertyApiInterface api;

    public ProprietorModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void loadProprietorByUsername(OnLoadProprietorListener listener, String token, String username) {
        Call<Proprietor> callProprietor = api.getProprietorByUsername(token, username);

        callProprietor.enqueue(new Callback<Proprietor>() {
            @Override
            public void onResponse(@NonNull Call<Proprietor> call, @NonNull Response<Proprietor> response) {
                Proprietor proprietor = response.body();
                listener.onLoadProprietorSuccess(proprietor);
            }

            @Override
            public void onFailure(@NonNull Call<Proprietor> call, @NonNull Throwable t) {
                Log.e("getProprietor", Objects.requireNonNull(t.getMessage()));
                listener.onLoadProprietorError(SERVER_ERROR);
            }
        });
    }
}
