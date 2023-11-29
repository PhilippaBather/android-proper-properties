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

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.map.MapUtils;

/**
 * SearchMapView - la actividad para buscar inmuebles disponsibles presentados en una mapa y ver
 * dichos muebles en una lista
 *
 * @author Philippa Bather
 */
public class SearchMapView extends AppCompatActivity implements Style.OnStyleLoaded, OnMapClickListener {

    private RadioButton rbtnBuy;
    private RadioButton rbtnRent;
    private RadioGroup radioGroup;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map
    private GesturesPlugin gesturesPlugin; // MapBox libraries - for user interaction with map


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_map);
        findViews();
        radioGroup.setOnCheckedChangeListener((grp, id) -> handleSearchType(grp, id));

        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

    }

    private void findViews() {
        rbtnBuy = findViewById(R.id.rbtn_search_map_buy);
        rbtnRent = findViewById(R.id.rbtn_search_map_rent);
        radioGroup = findViewById(R.id.rg_search_map_search_type);
        mapView = findViewById(R.id.mapView);
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
        // TODO: placeholder
        if(rbtnBuy.isChecked()) {
            Toast.makeText(this, "Buy selected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Rent selected", Toast.LENGTH_LONG).show();
        }
    }
}
