package com.philippabather.properproperties.view;

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

public class PropertyUpdateView extends AppCompatActivity implements PropertyUpdateContract.View {
    private FragmentRentalUpdate rentalUpdateFragment;

    private FragmentSaleUpdate saleUpdateFragment;
    private RentalProperty rental;
    private SaleProperty sale;
    private PropertyStatus propertyStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_property);

        Intent intent = getIntent();
        propertyStatus = PropertyStatus.valueOf(intent.getStringExtra("propertyStatus"));

        goToFragment(intent, propertyStatus);
    }
    private void goToFragment(Intent intent, PropertyStatus status) {
        if(PropertyStatus.RENTAL.equals(status)) {
            rental = intent.getParcelableExtra("property");
            rentalUpdateFragment = new FragmentRentalUpdate();
            Bundle bundle = new Bundle();
            bundle.putParcelable("rental", rental);
            rentalUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalUpdateFragment).commit();
        } else if (PropertyStatus.SALE.equals(status)) {
            sale = intent.getParcelableExtra("property");
            saleUpdateFragment = new FragmentSaleUpdate();
            Bundle bundle = new Bundle();
            bundle.putParcelable("sale", sale);
            saleUpdateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleUpdateFragment).commit();
        } else {
            Log.e("fragmentError", "Property Status not recognised.");
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
