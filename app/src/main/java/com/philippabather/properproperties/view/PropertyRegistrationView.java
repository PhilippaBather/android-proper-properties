package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyRegistrationContract;

import java.util.Objects;

public class PropertyRegistrationView extends AppCompatActivity implements PropertyRegistrationContract.View{

    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup rgAddProperty;

    private FragmentRentalAdd rentalFragment;
    private FragmentSaleAdd saleFragment;
    private long proprietorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        Intent intent = getIntent();
        proprietorId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra(INTENT_EXTRA_PROPRIETOR_ID)));

        findViews();
        rgAddProperty.setOnCheckedChangeListener(this::handlePropertyType);

        rentalFragment = new FragmentRentalAdd();
        saleFragment = new FragmentSaleAdd();
    }

    private void findViews() {
        rbtnBuy = findViewById(R.id.rbtn_add_sale);
        rbtnRent = findViewById(R.id.rbtn_add_rental);
        rgAddProperty = findViewById(R.id.rg_add_property);
    }

    private void handlePropertyType(RadioGroup grp, int id) {
        if (rbtnRent.isChecked()) {
            // infla el Fragment, remplazando el otro fragment si existe
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            rentalFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, rentalFragment).commit();
        } else {
            // infla el Fragment, remplazando el otro fragment si existe
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            saleFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleFragment).commit();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, R.string.api_msg_post, Toast.LENGTH_LONG).show();
    }

}
