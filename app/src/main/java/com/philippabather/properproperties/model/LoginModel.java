package com.philippabather.properproperties.model;

import static com.philippabather.properproperties.constants.Constants.LOG_CREDENTIALS_INVALID;
import static com.philippabather.properproperties.constants.Constants.SERVER_ERROR;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.LoginRequest;
import com.philippabather.properproperties.domain.LoginResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginModel - model para el login temporal.
 *
 * @author Philippa Bather
 */
public class LoginModel implements LoginContract.Model {

    private final PropertyApiInterface api;

    public LoginModel() {
        this.api = PropertyApi.buildInstance();
    }

    @Override
    public void authenticateUserByUsernameAndPassword(OnLoadAuthenticationListener listener, LoginRequest loginRequest) {
        Call<LoginResponse> callLogin = api.authenticateUser(loginRequest);

        callLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (loginResponse != null && loginResponse.getUsername() != null && loginResponse.getToken() != null) {
                } else {
                    Log.e("authenticateUser", LOG_CREDENTIALS_INVALID);
                }
                listener.onLoadAuthenticationSuccess(loginResponse);
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.e("authenticateUser", Objects.requireNonNull(t.getMessage()));
                listener.onAuthenticationError(SERVER_ERROR);
            }
        });
    }
}
