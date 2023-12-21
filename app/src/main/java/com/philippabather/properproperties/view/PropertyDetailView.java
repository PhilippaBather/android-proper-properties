package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_RENTAL_ID;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_SALE_ID;
import static com.philippabather.properproperties.constants.Constants.cross;
import static com.philippabather.properproperties.constants.Constants.euro;
import static com.philippabather.properproperties.constants.Constants.tick;
import static java.lang.Long.parseLong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyDetailContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.map.MapUtils;
import com.philippabather.properproperties.presenter.PropertyDetailPresenter;

public class PropertyDetailView extends AppCompatActivity implements PropertyDetailContract.View, Style.OnStyleLoaded{

    private ImageView ivPropertyImage;
    private TextView tvPropertyTitle;
    private TextView tvPropertyOverview;
    private TextView tvPropertyDescription;
    private TextView tvPropertyPrice;
    private TextView tvPropertyCharacteristics;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map

    private PropertyDetailPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_details);
        findViews();

        presenter = new PropertyDetailPresenter(this);

        getDataFromIntent();


        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);
    }

    private void findViews() {
        ivPropertyImage = findViewById(R.id.iv_property_detail_image);
        tvPropertyTitle = findViewById(R.id.tv_property_detail_title);
        tvPropertyOverview = findViewById(R.id.tv_property_detail_overview);
        tvPropertyDescription = findViewById(R.id.tv_property_detail_description);
        tvPropertyCharacteristics = findViewById(R.id.tv_property_detail_basic_characteristics);
        tvPropertyPrice = findViewById(R.id.tv_property_detail_price);
        mapView = findViewById(R.id.mapView);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        String rentalIdStr = intent.getStringExtra(INTENT_EXTRA_RENTAL_ID);
        String saleIdStr = intent.getStringExtra(INTENT_EXTRA_SALE_ID);

        if (rentalIdStr != null) {
            presenter.loadSelectedRentalProperty(parseLong(rentalIdStr));
        } else if (saleIdStr != null) {
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
        } else if (item.getItemId() == R.id.mi_action_search_map) {
            intent = new Intent(this, PropertyListView.class);
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
    public void listRentalProperty(RentalProperty loadedRental) {
        loadRentalData(loadedRental);
    }

    @Override
    public void listSaleProperty(SaleProperty loadedSale) {
        loadSaleData(loadedSale);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void loadRentalData(RentalProperty property) {
        // añade las detalles del inmueble a los TextViews
        ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        tvPropertyTitle.setText(String.format("%s : %s", property.getPropertyStatus(), property.getPropertyType()));
        tvPropertyPrice.setText(String.format("%.2f %c p/m", property.getRentPerMonth(), euro));
        int numBedrooms = property.getNumBedrooms();
        int metresSqr = property.getMetresSqr();
        boolean isLift = property.isLift();
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ? tick : cross));
        tvPropertyOverview.setText(overview);
        tvPropertyDescription.setText(property.getDescription());

        // pone el marcador de la ubicación del inmueble en la mapa
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        MapUtils.addMarker(pointAnnotationManager, marker, property.getLatitude(), property.getLongitude());
    }

    private void loadSaleData(SaleProperty property) {
        // añade las detalles del inmueble a los TextViews
        ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        tvPropertyTitle.setText(String.format("%s : %s", property.getPropertyStatus(), property.getPropertyType()));
        tvPropertyPrice.setText(String.format("%.2f %c p/m", property.getPrice(), euro));
        int numBedrooms = property.getNumBedrooms();
        int metresSqr = property.getMetresSqr();
        boolean isLift = property.isLift();
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ? tick : cross));
        tvPropertyOverview.setText(overview);
        tvPropertyDescription.setText(property.getDescription());

        // pone el marcador de la ubicación del inmueble en la mapa
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        MapUtils.addMarker(pointAnnotationManager, marker, property.getLatitude(), property.getLongitude());
    }

}
