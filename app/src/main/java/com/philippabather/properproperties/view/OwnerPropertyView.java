package com.philippabather.properproperties.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.ManagementContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.presenter.ManagementPresenter;

import java.util.ArrayList;
import java.util.List;

public class ManagePropertyView extends AppCompatActivity implements ManagementContract.View {
    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup radioGroup;

    private Proprietor proprietor;
    private List<RentalProperty> rentalPropertyList;
    private List<SaleProperty> salePropertyList;

    private RentalFragment rentalFragment;
    private SaleFragment saleFragment;

    private ManagementPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_management);
        findViews();
        radioGroup.setOnCheckedChangeListener(this::handleSearchType);

        rentalPropertyList = new ArrayList<>();
        salePropertyList = new ArrayList<>();

        // create instances of fragments
        rentalFragment = new RentalFragment();
        saleFragment = new SaleFragment();

        presenter = new ManagementPresenter(this);

    }

    private void findViews() {
        rbtnBuy = findViewById(R.id.rbtn_search_map_buy);
        rbtnRent = findViewById(R.id.rbtn_search_map_rent);
        radioGroup = findViewById(R.id.rg_search_map_search_type);
    }

    private void handleSearchType(RadioGroup grp, int id) {
        // TODO - quita hardcoding en la segunda entrega (por la implementación de login)
        long userId = 1;

        if (rbtnBuy.isChecked()) {
            rentalPropertyList.clear();
            presenter.loadProprietor(userId);

            // crea un bundle para enviar datos al Fragment
            rentalPropertyList = proprietor.getRentalPropertyList();
            ArrayList<RentalProperty> rentals = new ArrayList<RentalProperty>(rentalPropertyList);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("rentals", rentals);

            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleFragment).commit();
        } else {
            salePropertyList.clear();
            presenter.loadProprietor(userId);

            // crea un bundle para enviar datos al Fragment
            salePropertyList = proprietor.getSalePropertyList();
            ArrayList<SaleProperty> sales = new ArrayList<>(salePropertyList);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("sales", sales);

            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, saleFragment).commit();
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
        } else if (item.getItemId() == R.id.mi_action_search_map) {
            intent = new Intent(this, PropertyListView.class);
        } else {
            return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }
    @Override
    public void getProprietor(Proprietor proprietor) {
        this.proprietor = proprietor;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}