package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.LoginRequest;
import com.philippabather.properproperties.domain.LoginResponse;
import com.philippabather.properproperties.domain.SessionManager;
import com.philippabather.properproperties.model.LoginModel;
import com.philippabather.properproperties.view.LoginView;

/**
 * LoginPresenter - presenter temporal para manejar el login.
 *
 * @author Philippa Bather
 */
public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnLoadAuthenticationListener {

    private final LoginModel model;
    private final LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void onLoadAuthenticationSuccess(LoginResponse loginResponse) {
        view.getUserSession(loginResponse);

    }

    @Override
    public void onAuthenticationError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void authenticateUser(LoginRequest loginRequest) {
        model.authenticateUserByUsernameAndPassword(this, loginRequest);
    }

}
