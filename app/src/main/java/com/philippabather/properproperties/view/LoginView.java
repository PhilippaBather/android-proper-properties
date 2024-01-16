package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.LOGIN_ERROR;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.LoginRequest;
import com.philippabather.properproperties.domain.LoginResponse;
import com.philippabather.properproperties.domain.SessionManager;
import com.philippabather.properproperties.presenter.LoginPresenter;

/**
 * LoginView - la actividad que maneja la vista de un login temporal.
 *
 * @author Philppa Bather
 */
public class LoginView extends AppCompatActivity implements LoginContract.View {
    private Button btnBack;
    private Button btnLogin;
    private EditText etPassword;
    private EditText etUsername;
    private LoginPresenter presenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setOnClickListeners();
        presenter = new LoginPresenter(this);
        sessionManager = new SessionManager(LoginView.this);

        checkSession();
    }

    private void findViews() {
        btnBack = findViewById(R.id.btn_back);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_login_password);
        etUsername = findViewById(R.id.et_login_username);
    }

    private void setOnClickListeners(){
        btnBack.setOnClickListener(this::goBackToHomeActivity);
        btnLogin.setOnClickListener(this::handleLogin);
    }

    private void goBackToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeView.class);
        startActivity(intent);
    }

    private void checkSession() {
        if (sessionManager.getUsername() != null && sessionManager.isLoggedIn()) {
            goToOwnerPropertyView();
        }
    }

    private void handleLogin(View view) {
        String password = etPassword.getText().toString();
        String username = etUsername.getText().toString();

        if (password.trim() == "" && username.trim() == "") {
            showMessage(LOGIN_ERROR);
        }
        LoginRequest loginRequest = new LoginRequest(username, password);
        presenter.authenticateUser(loginRequest);
    }

    @Override
    public void getUserSession(LoginResponse loginResponse) {
        if (loginResponse == null) {
            Toast.makeText(this, "Credentials invalid", Toast.LENGTH_SHORT).show();
        } else {
            loginResponse.setLoggedIn(true);
            sessionManager.saveSession(loginResponse);
            goToOwnerPropertyView();
        }
    }

    private void goToOwnerPropertyView() {
        Intent intent = new Intent(this, OwnerPropertyView.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
