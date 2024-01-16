package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.LoginRequest;
import com.philippabather.properproperties.domain.LoginResponse;
import com.philippabather.properproperties.domain.SessionManager;

/**
 * LoginContract - contrato temporal para manejar el login de usuario.
 *
 * @author Philippa Bather
 */
public interface LoginContract {

    interface Model {
        interface OnLoadAuthenticationListener {
            void onLoadAuthenticationSuccess(LoginResponse loginResponse);
            void onAuthenticationError(String msg);
        }

        void authenticateUserByUsernameAndPassword(OnLoadAuthenticationListener listener, LoginRequest loginRequest);

    }

    interface View {
        void getUserSession(LoginResponse loginResponse);

        void showMessage(String msg);
    }

    interface Presenter {
        void authenticateUser(LoginRequest loginRequest);
    }
}
