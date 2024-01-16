package com.philippabather.properproperties.view;

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
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SessionManager;
import com.philippabather.properproperties.map.MapUtils;
import com.philippabather.properproperties.presenter.PropertyRegistrationPresenter;
import com.philippabather.properproperties.utils.SpinnerUtils;

import java.math.BigDecimal;

/**
 * RentalAddFragment - el fragmento para añadir un inmueble de alquiler.
 *
 * @author Philippa Bather
 */
public class RentalAddFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        Style.OnStyleLoaded, OnMapClickListener {

    private Button btnAdd;
    private Button btnBack;
    private CheckBox cbFurniture;
    private CheckBox cbLift;
    private CheckBox cbParking;
    private CheckBox cbPets;
    private EditText etDeposit;
    private EditText etDescription;
    private EditText etMinTenancy;
    private EditText etNumBedrooms;
    private EditText etNumBathrooms;
    private EditText etPrice;
    private EditText etSize;
    private Spinner spPropertyType;
    private PropertyType propertyType;
    private double latitude;
    private double longitude;
    private long proprietorId;
    private PropertyRegistrationPresenter presenter;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries
    private GesturesPlugin gesturesPlugin;
    private Bitmap bitmap;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_rental_add, container, false);

//        proprietorId = getArguments().getLong(INTENT_EXTRA_PROPRIETOR_ID);

        findViews(view);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.property_type, android.R.layout.simple_spinner_item);
        SpinnerUtils.setUpSpinner(adapter, spPropertyType, this);
        addOnClickListeners();
        setUpMap();

        sessionManager = new SessionManager(view.getContext());
        presenter = new PropertyRegistrationPresenter((PropertyRegistrationView) view.getContext());

        return view;
    }

    private void findViews(View view) {
        btnAdd = view.findViewById(R.id.btn_add);
        btnBack = view.findViewById(R.id.btn_cancel);
        cbFurniture = view.findViewById(R.id.cb_property_leasehold);
        cbLift = view.findViewById(R.id.cb_property_lift);
        cbParking = view.findViewById(R.id.cb_property_parking);
        cbPets = view.findViewById(R.id.cb_property_pets);
        etDeposit = view.findViewById(R.id.et_property_deposit);
        etDescription = view.findViewById(R.id.et_ml_property_description);
        etMinTenancy = view.findViewById(R.id.et_property_min_tenancy);
        etNumBathrooms = view.findViewById(R.id.et_property_num_bathrooms);
        etNumBedrooms = view.findViewById(R.id.et_property_num_bedrooms);
        etPrice = view.findViewById(R.id.et_property_price);
        etSize = view.findViewById(R.id.et_property_size);
        spPropertyType = view.findViewById(R.id.sp_property_type);
        mapView = view.findViewById(R.id.mapView);
    }

    private void addOnClickListeners() {
        btnAdd.setOnClickListener(this::addProperty);
        btnBack.setOnClickListener(this::cancel);
    }

    private void cancel(View view) {
        Intent intent = new Intent(view.getContext(), OwnerPropertyView.class);
        intent.putExtra(INTENT_EXTRA_PROPRIETOR_ID, String.valueOf(proprietorId));
        startActivity(intent);
    }

    private void addProperty(View view) {
        BigDecimal deposit = new BigDecimal(etDeposit.getText().toString());
        BigDecimal price = new BigDecimal(etPrice.getText().toString());
        boolean hasFurniture = cbFurniture.isChecked();
        boolean isLift = cbLift.isChecked();
        boolean isParking = cbParking.isChecked();
        boolean isPets = cbPets.isChecked();
        int minTen = Integer.parseInt(etMinTenancy.getText().toString());
        int numBathrooms = Integer.parseInt(etNumBathrooms.getText().toString());
        int numBedrooms = Integer.parseInt(etNumBedrooms.getText().toString());
        int size = Integer.parseInt(etSize.getText().toString());
        String description = etDescription.getText().toString();

        RentalProperty rental = new RentalProperty(PropertyStatus.RENTAL, propertyType, latitude, longitude,
                size, description, numBedrooms, numBathrooms, isParking, isLift,
                price, deposit, minTen, hasFurniture, isPets);

        presenter.createNewRentalProperty(sessionManager.getToken(), sessionManager.getUserId(), rental);
        resetViews();
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

    private void resetViews() {
        cbFurniture.setChecked(false);
        cbLift.setChecked(false);
        cbParking.setChecked(false);
        cbPets.setChecked(false);
        etDeposit.setText("");
        etDescription.setText("");
        etMinTenancy.setText("");
        etNumBathrooms.setText("");
        etNumBedrooms.setText("");
        etPrice.setText("");
        etSize.setText("");
        pointAnnotationManager.deleteAll();
    }

    private void setUpMap() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = initializePointAnnotationManager(mapView);

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        setCameraPositionAndZoom(mapView);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        latitude = point.latitude();
        longitude = point.longitude();
        MapUtils.addMarker(pointAnnotationManager, bitmap, latitude, longitude);
        return false;
    }

}