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

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.PropertyAdapter;
import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.map.MapUtils;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchMapView - la actividad para buscar inmuebles disponsibles presentados en una mapa y ver
 * dichos muebles en una lista
 *
 * @author Philippa Bather
 */
public class PropertyListView extends AppCompatActivity implements PropertyListContract.View, Style.OnStyleLoaded, OnMapClickListener {

    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup radioGroup;
    private MapView mapView;

    private List<Property> propertyList;
    private PropertyAdapter propertyAdapter;
    private PropertyListPresenter propertiesListPresenter;

    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map
    private GesturesPlugin gesturesPlugin; // MapBox libraries - for user interaction with map


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_list);
        findViews();
        radioGroup.setOnCheckedChangeListener((grp, id) -> handleSearchType(grp, id));

        propertyList = new ArrayList<>();
        propertiesListPresenter = new PropertyListPresenter(this);

        setUpRecyclerView();

        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(PropertyListView.this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        // https://developer.android.com/guide/components/processes-and-threads#java
        new Thread(() -> loadData()).start();
    }

    private void findViews() {
        rbtnBuy = findViewById(R.id.rbtn_search_map_buy);
        rbtnRent = findViewById(R.id.rbtn_search_map_rent);
        radioGroup = findViewById(R.id.rg_search_map_search_type);
        mapView = findViewById(R.id.mapView);
    }

    private void setUpRecyclerView() {
        // https://developer.android.com/guide/components/processes-and-threads#java
        new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recyclerview_property_list);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PropertyListView.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            propertyAdapter = new PropertyAdapter(propertyList, propertiesListPresenter);
            recyclerView.setAdapter(propertyAdapter);
        }).start();
    }

    private void loadData() {
        propertiesListPresenter.loadAllProperties();
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

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        MapUtils.setCameraPositionAndZoom(mapView);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        return false;
    }

    private void handleSearchType(RadioGroup grp, int id) {
        if (rbtnBuy.isChecked()) {
            Toast.makeText(this, "Buy selected", Toast.LENGTH_LONG).show();
            // TODO
//            new Thread(() -> propertiesListPresenter.loadAllSaleProperties()).start();
        } else {
            // TODO
            Toast.makeText(this, "Rent selected", Toast.LENGTH_LONG).show();
//            new Thread(() -> propertiesListPresenter.loadAllRentalProperties()).start();
        }
    }

    @Override
    public void listProperties(List<Property> properties) {
        propertyList.clear();
        propertyList.addAll(properties);
        propertyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
