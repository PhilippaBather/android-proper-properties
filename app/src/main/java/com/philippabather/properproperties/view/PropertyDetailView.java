package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL_ID;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE_ID;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_RENTAL_ID;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_SALE_ID;
import static java.lang.Long.parseLong;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyDetailContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.presenter.PropertyDetailPresenter;

/**
 * PropertyDetailView - la actividad maneja las detalles de un inmueble para un usuario.
 *
 * @author Philippa Bather
 */
public class PropertyDetailView extends AppCompatActivity implements PropertyDetailContract.View {

    private View layoutPropertyDetails;
    private RentalClientDetailFragment rentalFragment;
    private SaleClientDetailFragment saleFragment;

    private PropertyDetailPresenter presenter;

    private long rentalId;
    private long saleId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        layoutPropertyDetails = findViewById(R.id.layout_property_details);
        presenter = new PropertyDetailPresenter(this);
        getDataFromIntent();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        String rentalIdStr = intent.getStringExtra(INTENT_EXTRA_RENTAL_ID);
        String saleIdStr = intent.getStringExtra(INTENT_EXTRA_SALE_ID);

        if (rentalIdStr != null) {
            rentalId = Long.parseLong(rentalIdStr);
            presenter.loadSelectedRentalProperty(parseLong(rentalIdStr));
        } else if (saleIdStr != null) {
            saleId = Long.parseLong(saleIdStr);
            presenter.loadSelectedSaleProperty(parseLong(saleIdStr));
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
    public void listRentalProperty(RentalProperty loadedRental) {
        rentalFragment = new RentalClientDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ARGUMENT_RENTAL, loadedRental);
        bundle.putLong(BUNDLE_ARGUMENT_RENTAL_ID, rentalId);
        rentalFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalFragment).commit();
    }

    @Override
    public void listSaleProperty(SaleProperty loadedSale) {
        saleFragment = new SaleClientDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ARGUMENT_SALE, loadedSale);
        bundle.putLong(BUNDLE_ARGUMENT_SALE_ID, saleId);
        saleFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleFragment).commit();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(layoutPropertyDetails, msg, Snackbar.LENGTH_LONG).show();
    }

}
