package com.philippabather.properproperties.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.RentalPropertyAdapter;
import com.philippabather.properproperties.adapter.SalePropertyAdapter;
import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.Role;
import com.philippabather.properproperties.domain.SaleFavourite;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * PropertyListView - la actividad para buscar inmuebles disponsibles presentados en una lista según
 * de estado: 'rent' (de alquiler) o 'sale' (para vender).
 *
 * @author Philippa Bather
 */
public class PropertyListView extends AppCompatActivity implements PropertyListContract.View {

    private RadioButton rbtnBuy;
    private RadioGroup radioGroup;

    private List<RentalProperty> rentalPropertyList;
    private List<SaleProperty> salePropertyList;
    private List<RentalFavourite> rentalFavourites;
    private List<SaleFavourite> saleFavourites;
    private RentalPropertyAdapter rentalPropertyAdapter;
    private SalePropertyAdapter salePropertyAdapter;
    private PropertyListPresenter propertiesListPresenter;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerViewRental;
    private RecyclerView recyclerViewSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_list);
        findViews();
        radioGroup.setOnCheckedChangeListener(this::handleSearchType);

        rentalPropertyList = new ArrayList<>();
        salePropertyList = new ArrayList<>();
        rentalFavourites = new ArrayList<>();
        saleFavourites = new ArrayList<>();
        propertiesListPresenter = new PropertyListPresenter(this);

        recyclerViewRental = findViewById(R.id.recyclerview_rental_property_list);
        recyclerViewRental.setHasFixedSize(true);
        recyclerViewSale = findViewById(R.id.recyclerview_sale_property_list);
        recyclerViewSale.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(PropertyListView.this);

        // attach layout manager to rental recycler view as default view
        recyclerViewRental.setLayoutManager(linearLayoutManager);

        // create adapters
        rentalPropertyAdapter = new RentalPropertyAdapter(rentalPropertyList, rentalFavourites, Role.CLIENT, 0);
        salePropertyAdapter = new SalePropertyAdapter(salePropertyList, saleFavourites, Role.CLIENT, 0);
        recyclerViewRental.setAdapter(rentalPropertyAdapter);
        recyclerViewSale.setAdapter(salePropertyAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // https://developer.android.com/guide/components/processes-and-threads#java
        new Thread(() -> propertiesListPresenter.loadRentalProperties()).start();
        propertiesListPresenter.loadFavouriteRentals();
        propertiesListPresenter.loadFavouriteSales();
    }

    private void findViews() {
        rbtnBuy = findViewById(R.id.rbtn_search_map_buy);
        radioGroup = findViewById(R.id.rg_search_map_search_type);
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
        Intent intent = new Intent();

        if (item.getItemId() == R.id.mi_action_home) {
            intent = new Intent(this, HomeView.class);
        } else if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            intent = new Intent(this, MortgageCheckerView.class);
        } else {
            return super.onOptionsItemSelected(item);
        }

        startActivity(intent);
        return true;
    }

    private void handleSearchType(RadioGroup grp, int id) {
        if (rbtnBuy.isChecked()) {
            rentalPropertyList.clear();
            recyclerViewRental.setLayoutManager(null);
            recyclerViewSale.setLayoutManager(linearLayoutManager);
            new Thread(() -> propertiesListPresenter.loadSaleProperties()).start();
        } else {
            salePropertyList.clear();
            recyclerViewSale.setLayoutManager(null);
            recyclerViewRental.setLayoutManager(linearLayoutManager);
            new Thread(() -> propertiesListPresenter.loadRentalProperties()).start();
        }
    }

    @Override
    public void listRentalProperties(List<RentalProperty> properties) {
        rentalPropertyList.clear();
        rentalPropertyList.addAll(properties);
        rentalPropertyAdapter.notifyDataSetChanged();
        salePropertyAdapter.notifyDataSetChanged();
    }

    @Override
    public void listSaleProperties(List<SaleProperty> properties) {
        salePropertyList.clear();
        salePropertyList.addAll(properties);
        salePropertyAdapter.notifyDataSetChanged();
        rentalPropertyAdapter.notifyDataSetChanged();
    }

    @Override
    public void listFavouriteRentals(List<RentalFavourite> properties) {
        rentalFavourites.clear();
        rentalFavourites.addAll(properties);
        rentalPropertyAdapter.notifyDataSetChanged();
    }

    @Override
    public void listFavouriteSales(List<SaleFavourite> properties) {
        saleFavourites.clear();
        saleFavourites.addAll(properties);
        salePropertyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
