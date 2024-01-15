package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.ProprietorContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.domain.SessionManager;
import com.philippabather.properproperties.presenter.ProprietorPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * OwnerPropertyView - la actividad maneja la página principal para un propietario.
 *
 * @author Philippa Bather
 */
public class OwnerPropertyView extends AppCompatActivity implements ProprietorContract.View {
    private Button btnLogout;
    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup radioGroup;
    private FloatingActionButton flBtnAddProperty;

    private Proprietor proprietor;
    private List<RentalProperty> rentalPropertyList;
    private List<SaleProperty> salePropertyList;

    private RecyclerViewOwnerRentalFragment recyclerViewOwnerRentalFragment;
    private RecyclerViewOwnerSaleFragment recyclerViewOwnerSaleFragment;
    private ProprietorPresenter presenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_management);
        findViews();
        btnLogout.setOnClickListener(this::handleLogout);
        radioGroup.setOnCheckedChangeListener(this::handlePropertySelection);
        flBtnAddProperty.setOnClickListener(v -> addProperty());

        rentalPropertyList = new ArrayList<>();
        salePropertyList = new ArrayList<>();

        // create instances of fragments
        recyclerViewOwnerRentalFragment = new RecyclerViewOwnerRentalFragment();
        recyclerViewOwnerSaleFragment = new RecyclerViewOwnerSaleFragment();

        // get session
        sessionManager = new SessionManager(OwnerPropertyView.this);
        String token = sessionManager.getToken();
        String username = sessionManager.getUsername();

        // fetch property details
        presenter = new ProprietorPresenter(this);
        if (token != null && username != null) {
            presenter.loadProprietor(token, username);
        }
    }

    private void findViews() {
        btnLogout = findViewById(R.id.btn_logout);
        rbtnBuy = findViewById(R.id.rbtn_search_map_buy);
        rbtnRent = findViewById(R.id.rbtn_search_map_rent);
        radioGroup = findViewById(R.id.rg_search_map_search_type);
        flBtnAddProperty = findViewById(R.id.fl_btn_add_property);
    }

    private void handleLogout(View view) {
        sessionManager.deleteSession();
        Intent intent = new Intent(this, LoginView.class);
        startActivity(intent);
    }


    private void handlePropertySelection(RadioGroup grp, int id) {
        long proprietorId = 1; // TODO update
        if (rbtnRent.isChecked()) {
            rentalPropertyList.clear();
            // crea un bundle para enviar datos al Fragment
            rentalPropertyList = proprietor.getRentalPropertyList();
            ArrayList<RentalProperty> rentals = new ArrayList<RentalProperty>(rentalPropertyList);
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            bundle.putParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS, rentals);
            recyclerViewOwnerRentalFragment.setArguments(bundle);
            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, recyclerViewOwnerRentalFragment).commit();
        } else {
            salePropertyList.clear();
            // crea un bundle para enviar datos al Fragment
            salePropertyList = proprietor.getSalePropertyList();
            ArrayList<SaleProperty> sales = new ArrayList<>(salePropertyList);
            Bundle bundle = new Bundle();
            bundle.putLong(INTENT_EXTRA_PROPRIETOR_ID, proprietorId);
            bundle.putParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES, sales);
            recyclerViewOwnerSaleFragment.setArguments(bundle);
            // infla el Fragment, remplazando el otro fragment si existe
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_management, recyclerViewOwnerSaleFragment).commit();
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
        sessionManager.setUserId(proprietor.getId());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void addProperty() {
        Intent intent = new Intent(this, PropertyRegistrationView.class);
        startActivity(intent);
    }
}