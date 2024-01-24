package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY_STATUS;
import static com.philippabather.properproperties.constants.Constants.LOG_PROPERTY_STATUS_ERROR;
import static com.philippabather.properproperties.constants.Constants.LOG_TAG_FRAGMENT_ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyUpdateContract;
import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

/**
 * PropertyUpdateView - la actividad para maneja la vista de actualización de inmuebles.
 *
 * @author Philippa Bather
 */
public class PropertyUpdateView extends AppCompatActivity implements PropertyUpdateContract.View {

    private View layoutUpdateProperty;
    private RentalUpdateFragment rentalUpdateFragment;
    private SaleUpdateFragment saleUpdateFragment;
    private RentalProperty rental;
    private SaleProperty sale;
    private PropertyStatus propertyStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_property);
        layoutUpdateProperty = findViewById(R.id.layout_activity_update_property);

        Intent intent = getIntent();
        propertyStatus = PropertyStatus.valueOf(intent.getStringExtra(INTENT_EXTRA_PROPERTY_STATUS));

        goToFragment(intent, propertyStatus);
    }
    private void goToFragment(Intent intent, PropertyStatus status) {
        if(PropertyStatus.RENTAL.equals(status)) {
            rental = intent.getParcelableExtra(INTENT_EXTRA_PROPERTY);
            rentalUpdateFragment = new RentalUpdateFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_ARGUMENT_RENTAL, rental);
            rentalUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalUpdateFragment).commit();
        } else if (PropertyStatus.SALE.equals(status)) {
            sale = intent.getParcelableExtra(INTENT_EXTRA_PROPERTY);
            saleUpdateFragment = new SaleUpdateFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_ARGUMENT_SALE, sale);
            saleUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleUpdateFragment).commit();
        } else {
            Log.e(LOG_TAG_FRAGMENT_ERROR, LOG_PROPERTY_STATUS_ERROR);
        }
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
        } else if (item.getItemId() == R.id.mi_action_property_list) {
            intent = new Intent(this, PropertyListView.class);
        } else if (item.getItemId() == R.id.mi_action_login || item.getItemId() == R.id.mi_action_owner_portal) {
            intent = new Intent(this, LoginView.class);
        } else {
            return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(layoutUpdateProperty, msg, Snackbar.LENGTH_LONG).show();
    }
}
