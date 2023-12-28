package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;
import static com.philippabather.properproperties.constants.Constants.LOGIN_ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.LoginContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.presenter.LoginPresenter;

/**
 * LoginView - la actividad que maneja la vista de un login temporal.
 *
 * @author Philppa Bather
 */
public class LoginView extends AppCompatActivity implements LoginContract.View {
    // TODO - 2 entrega: implementa funcionalidad de un login seguro
    private Button btnBack;
    private Button btnLogin;
    private EditText etPassword;
    private EditText etUsername;
    private String password;
    private String username;
    private LoginPresenter presenter;
    private Proprietor proprietor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setOnClickListeners();
        presenter = new LoginPresenter(this);
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

    private void handleLogin(View view) {
        password = etPassword.getText().toString();
        username = etUsername.getText().toString();

        if (password.trim() == "" && username.trim() == "") {
            showMessage(LOGIN_ERROR);
        }
        presenter.loadProprietorByUsernameAndPassword(username, password);
    }




    @Override
    public void getProprietor(Proprietor proprietor) {
        this.proprietor = proprietor;
        if (proprietor != null) {
            Intent intent = new Intent(this, OwnerPropertyView.class);
            intent.putExtra(INTENT_EXTRA_PROPRIETOR_ID, String.valueOf(proprietor.getId()));
            startActivity(intent);
        } else {
            showMessage(LOGIN_ERROR);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
