package com.philippabather.properproperties.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyRegistrationContract;

/**
 * PropertyRegistrationView - la actividad maneja la vista para registración de un inmueble por un
 * propeitario.
 *
 * @author Philippa Bather
 */
public class PropertyRegistrationView extends AppCompatActivity implements PropertyRegistrationContract.View{

    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup rgAddProperty;
    private View layoutAddProperty;

    private RentalAddFragment rentalFragment;
    private SaleAddFragment saleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        findViews();
        rgAddProperty.setOnCheckedChangeListener(this::handlePropertyType);

        rentalFragment = new RentalAddFragment();
        saleFragment = new SaleAddFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();

        if (item.getItemId() == R.id.mi_action_home) {
            intent = new Intent(this, HomeView.class);
        } else if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            intent = new Intent(this, MortgageCheckerView.class);
        } else if (item.getItemId() == R.id.mi_action_search_map) {
            intent = new Intent(this, PropertyListView.class);
        } else {
            return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }


    private void findViews() {
        layoutAddProperty = findViewById(R.id.layout_add_property);
        rbtnBuy = findViewById(R.id.rbtn_add_sale);
        rbtnRent = findViewById(R.id.rbtn_add_rental);
        rgAddProperty = findViewById(R.id.rg_add_property);
    }

    private void handlePropertyType(RadioGroup grp, int id) {
        if (rbtnRent.isChecked()) {
            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalFragment).commit();
        } else {
            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleFragment).commit();
        }
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(layoutAddProperty, msg, Snackbar.LENGTH_LONG).show();
    }

}
