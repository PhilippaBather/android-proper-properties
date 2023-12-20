package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.cross;
import static com.philippabather.properproperties.constants.Constants.euro;
import static com.philippabather.properproperties.constants.Constants.tick;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;


import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.contract.PropertyDetailContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.map.MapUtils;
import com.philippabather.properproperties.presenter.PropertyDetailPresenter;

//, Style.OnStyleLoaded, OnMapClickListener
public class RentalDetailView extends AppCompatActivity implements PropertyDetailContract.View, Style.OnStyleLoaded, OnMapClickListener {

    private ImageView ivPropertyImage;
    private TextView tvPropertyTitle;
    private TextView tvPropertyOverview;
    private TextView tvPropertyDescription;
    private TextView tvPropertyPrice;
    private TextView tvPropertyCharacteristics;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map
    private GesturesPlugin gesturesPlugin; // MapBox libraries - for user interaction with map

    private PropertyDetailPresenter presenter;
    private RentalProperty rentalProperty;
    private SaleProperty saleProperty;
    private double rentalLat;
    private double rentalLong;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_details);
        findViews();

        presenter = new PropertyDetailPresenter(this);

        getDataFromIntent();


        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
//        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
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
//        String rentalLat = intent.getStringExtra("rental_lat");
//        String rentalLong = intent.getStringExtra("rental_lon");
//        this.rentalLat = parseDouble(rentalLat);
//        this.rentalLong = parseDouble(rentalLong);

        String rentalIdStr = intent.getStringExtra("rental_property_id");
        String saleIdStr = intent.getStringExtra("sale_property_id");

        if (rentalIdStr != null) {
            presenter.loadSelectedRentalProperty(parseLong(rentalIdStr));
        } else if (saleIdStr != null) {
            presenter.loadSelectedSaleProperty(parseLong(saleIdStr));
        }

    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);
//        addMarker(rentalLat, rentalLong);
        MapUtils.setCameraPositionAndZoom(mapView);

    }

    private void addMarker(double rentalLat, double rentalLong) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(rentalLong, rentalLat))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view));
        pointAnnotationManager.create(pointAnnotationOptions);

    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        return false;
    }

    @Override
    public void listRentalProperty(RentalProperty loadedRental) {
        rentalProperty = loadedRental;
        loadRentalData(rentalProperty);
    }

    @Override
    public void listSaleProperty(SaleProperty loadedSale) {
        saleProperty = loadedSale;
        loadSaleData(saleProperty);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void loadRentalData(RentalProperty property) {
        ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        tvPropertyTitle.setText(String.format("%s : %s", property.getPropertyStatus(), property.getPropertyType()));
        tvPropertyPrice.setText(String.format("%.2f %c p/m", property.getRentPerMonth(), euro));
        int numBedrooms = property.getNumBedrooms();
        int metresSqr = property.getMetresSqr();
        boolean isLift = property.isLift();
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ? tick : cross));
        tvPropertyOverview.setText(overview);
        tvPropertyDescription.setText(property.getDescription());

        addMarker(property.getLatitude(), property.getLongitude());
    }

    private void loadSaleData(SaleProperty property) {
        ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        tvPropertyTitle.setText(String.format("%s : %s", property.getPropertyStatus(), property.getPropertyType()));
        tvPropertyPrice.setText(String.format("%.2f %c p/m", property.getPrice(), euro));
        int numBedrooms = property.getNumBedrooms();
        int metresSqr = property.getMetresSqr();
        boolean isLift = property.isLift();
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ? tick : cross));
        tvPropertyOverview.setText(overview);
        tvPropertyDescription.setText(property.getDescription());

        addMarker(property.getLatitude(), property.getLongitude());
    }

}
