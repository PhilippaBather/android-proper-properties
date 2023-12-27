package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;
import static com.philippabather.properproperties.map.MapUtils.initializePointAnnotationManager;
import static com.philippabather.properproperties.map.MapUtils.setCameraPositionAndZoom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.PropertyType;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.map.MapUtils;
import com.philippabather.properproperties.presenter.PropertyUpdatePresenter;
import com.philippabather.properproperties.utils.SpinnerUtils;

import java.math.BigDecimal;

public class FragmentSaleUpdate extends Fragment implements AdapterView.OnItemSelectedListener,
        Style.OnStyleLoaded, OnMapClickListener {

    private Button btnBack;
    private Button btnDelete;
    private Button btnUpdate;
    private CheckBox cbHasLift;
    private CheckBox cbIsLeasehold;
    private CheckBox cbHasParking;
    private EditText etDescription;
    private EditText etNumBathrooms;
    private EditText etNumBedrooms;
    private EditText etPrice;
    private EditText etSize;
    private Spinner spPropertyType;

    private PropertyType propertyType;
    private double latitude;
    private double longitude;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries
    private GesturesPlugin gesturesPlugin;
    private Bitmap bitmap;

    private PropertyUpdatePresenter presenter;
    private SaleProperty saleProperty;
    private long proprietorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale_update, container, false);
        findViews(view);

        assert getArguments() != null;
        saleProperty = getArguments().getParcelable(BUNDLE_ARGUMENT_SALE);
        proprietorId = getArguments().getLong(INTENT_EXTRA_PROPRIETOR_ID);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.property_type, android.R.layout.simple_spinner_item);
        SpinnerUtils.setUpSpinner(adapter, spPropertyType, this);
        setUpClickListeners();
        setUpMap();
        setFields();

        presenter = new PropertyUpdatePresenter((PropertyUpdateView) view.getContext());

        return view;
    }

    private void findViews(View view) {
        btnDelete = view.findViewById(R.id.btn_delete);
        btnBack = view.findViewById(R.id.btn_cancel);
        btnUpdate = view.findViewById(R.id.btn_update);
        cbHasLift = view.findViewById(R.id.cb_property_lift);
        cbIsLeasehold = view.findViewById(R.id.cb_property_leasehold);
        cbHasParking = view.findViewById(R.id.cb_property_parking);
        etDescription = view.findViewById(R.id.et_ml_property_description);
        etNumBathrooms = view.findViewById(R.id.et_property_num_bathrooms);
        etNumBedrooms = view.findViewById(R.id.et_property_num_bedrooms);
        etPrice = view.findViewById(R.id.et_property_price);
        etSize = view.findViewById(R.id.et_property_size);
        spPropertyType = view.findViewById(R.id.sp_property_type);
        mapView = view.findViewById(R.id.mapView);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemAtPosition = adapterView.getItemAtPosition(i);
        String type = (String) itemAtPosition;
        propertyType = SpinnerUtils.setPropertyType(type);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        propertyType = PropertyType.HOUSE;
    }

    private void setUpMap() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = initializePointAnnotationManager(mapView);

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

        MapUtils.addMarker(pointAnnotationManager, bitmap, saleProperty.getLatitude(), saleProperty.getLongitude());
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        latitude = point.latitude();
        longitude = point.longitude();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        MapUtils.addMarker(pointAnnotationManager, bitmap, latitude, longitude);
        return false;
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        setCameraPositionAndZoom(mapView);
    }

    private void setFields() {
        cbHasLift.setChecked(saleProperty.isLift());
        cbIsLeasehold.setChecked(saleProperty.isLeasehold());
        cbHasParking.setChecked(saleProperty.isParking());
        etDescription.setText(saleProperty.getDescription());
        etNumBathrooms.setText(String.valueOf(saleProperty.getNumBathrooms()));
        etNumBedrooms.setText(String.valueOf(saleProperty.getNumBedrooms()));
        etPrice.setText(String.valueOf(saleProperty.getPrice()));
        etSize.setText(String.valueOf(saleProperty.getMetresSqr()));
        latitude = saleProperty.getLatitude();
        longitude = saleProperty.getLongitude();
    }

    private void setUpClickListeners(){
        btnBack.setOnClickListener(this::goToOwnerPropertyView);
        btnDelete.setOnClickListener(this::handleDeleteProperty);
        btnUpdate.setOnClickListener(this::handleUpdateProperty);
    }

    private void goToOwnerPropertyView(View view) {
        Intent intent = new Intent(view.getContext(), OwnerPropertyView.class);
        intent.putExtra(INTENT_EXTRA_PROPRIETOR_ID, String.valueOf(proprietorId));
        startActivity(intent);
    }

    private void handleDeleteProperty(View view) {
        presenter.deleteSelectedProperty(saleProperty.getId(), PropertyStatus.SALE);
        goToOwnerPropertyView(view);
    }

    private void handleUpdateProperty(View view) {
        BigDecimal price = new BigDecimal(etPrice.getText().toString());
        boolean hasLift = cbHasLift.isChecked();
        boolean hasParking = cbHasParking.isChecked();
        boolean leasehold = cbIsLeasehold.isChecked();
        int numBathrooms = Integer.parseInt(etNumBathrooms.getText().toString());
        int numBedrooms = Integer.parseInt(etNumBedrooms.getText().toString());
        int size = Integer.parseInt(etSize.getText().toString());
        String description = etDescription.getText().toString();

        SaleProperty sale = new SaleProperty(PropertyStatus.SALE, propertyType, latitude, longitude,
                size, description, numBedrooms, numBathrooms, hasParking, hasLift, price, leasehold);

        presenter.updateSaleProperty(saleProperty.getId(), sale);
    }
}