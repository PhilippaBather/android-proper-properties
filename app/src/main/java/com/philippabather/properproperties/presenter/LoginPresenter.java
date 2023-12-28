package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.model.LoginModel;
import com.philippabather.properproperties.view.LoginView;

/**
 * LoginPresenter - presenter temporal para manejar el login.
 *
 * @author Philippa Bather
 */
public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnLoadProprietorListener {

    private final LoginModel model;
    private final LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void loadProprietorByUsernameAndPassword(String username, String password) {
        model.loadProprietorByUsernameAndPassword(this, username, password);
    }

    @Override
    public void onLoadProprietorSuccess(Proprietor proprietor) {
        view.getProprietor(proprietor);
    }

    @Override
    public void onLoadProprietorError(String msg) {
        view.showMessage(msg);
    }
}
