package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY_STATUS;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;
import static com.philippabather.properproperties.constants.Constants.LOG_PROPERTY_STATUS_ERROR;
import static com.philippabather.properproperties.constants.Constants.LOG_TAG_FRAGMENT_ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyUpdateContract;
import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.Objects;

/**
 * PropertyUpdateView - la actividad para maneja la vista de actualización de inmuebles.
 *
 * @author Philippa Bather
 */
public class PropertyUpdateView extends AppCompatActivity implements PropertyUpdateContract.View {
    private RentalUpdateFragment rentalUpdateFragment;

    private SaleUpdateFragment saleUpdateFragment;
    private RentalProperty rental;
    private SaleProperty sale;
    private PropertyStatus propertyStatus;
    private long proprietorId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_property);

        Intent intent = getIntent();
        proprietorId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra(INTENT_EXTRA_PROPRIETOR_ID)));
        propertyStatus = PropertyStatus.valueOf(intent.getStringExtra(INTENT_EXTRA_PROPERTY_STATUS));

        goToFragment(intent, propertyStatus);
    }
    private void goToFragment(Intent intent, PropertyStatus status) {
        if(PropertyStatus.RENTAL.equals(status)) {
            rental = intent.getParcelableExtra(INTENT_EXTRA_PROPERTY);
            rentalUpdateFragment = new RentalUpdateFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            bundle.putParcelable(BUNDLE_ARGUMENT_RENTAL, rental);
            rentalUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalUpdateFragment).commit();
        } else if (PropertyStatus.SALE.equals(status)) {
            sale = intent.getParcelableExtra(INTENT_EXTRA_PROPERTY);
            saleUpdateFragment = new SaleUpdateFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            bundle.putParcelable(BUNDLE_ARGUMENT_SALE, sale);
            saleUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleUpdateFragment).commit();
        } else {
            Log.e(LOG_TAG_FRAGMENT_ERROR, LOG_PROPERTY_STATUS_ERROR);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
