package com.philippabather.properproperties.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.MortgageChecker;

public class MortgageCheckerActivity extends AppCompatActivity {

    private Button btnCalculate;
    private TextInputEditText txtInputEditTxtDeposit;
    private TextInputEditText txtInputEditTxtInterestRate;
    private TextInputEditText txtInputEditTxtLoanDuration;
    private TextInputEditText txtInputEditTxtPostcode;
    private TextInputEditText txtInputEditTxtPropertyPrice;
    private TextView txtViewMortgageQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_advisor);

        findViews();
        btnCalculate.setOnClickListener((v) -> calculateMortgage());
    }

    /**
     * Infla el menú de ítems en action_bar.xml
     *
     * @param menu - el menú en el 'action bar'
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Maneja la seleción y la acción correspondiente de un ítem
     * en el action bar
     *
     * @param item ítem de menú
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            return true;
        } else if (item.getItemId() == R.id.mi_action_home) {
            goToHomeActivity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Encontra todas las vistas.
     */
    private void findViews() {
        btnCalculate = findViewById(R.id.btn_mortgage_adviser_calculate);

        txtInputEditTxtDeposit = findViewById(R.id.txt_fld_mortgage_adviser_deposit);
        txtInputEditTxtInterestRate = findViewById(R.id.txt_fld_mortgage_adviser_interest);
        txtInputEditTxtLoanDuration = findViewById(R.id.txt_fld_mortgage_adviser_loan);
        txtInputEditTxtPostcode = findViewById(R.id.txt_fld_mortgage_adviser_postcode);
        txtInputEditTxtPropertyPrice = findViewById(R.id.txt_fld_mortgage_adviser_price);

        txtViewMortgageQuote = findViewById(R.id.tv_mortgage_adviser_quote);
    }

    private boolean updateDepositPercentage() {
        return false;
    }

    /**
     * Comunica con el Presenter para calcular los pagos mensuales de una hipoteca.
     */
    private void calculateMortgage() {

        final int ONE_HUNDRED = 100;

        double price = Double.parseDouble(String.valueOf(txtInputEditTxtPropertyPrice.getText()));
        double deposit = Double.parseDouble(String.valueOf(txtInputEditTxtDeposit.getText()));
        double rate = Double.parseDouble(String.valueOf(txtInputEditTxtInterestRate.getText())) / ONE_HUNDRED;
        int duration = Integer.parseInt(String.valueOf(txtInputEditTxtLoanDuration.getText()));
        String postcode = String.valueOf(txtInputEditTxtPostcode);

        MortgageChecker quoteCalculator = new MortgageChecker(price, deposit, rate, duration, postcode);
        String quote = quoteCalculator.calculate();
        txtViewMortgageQuote.setText(quote);
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}