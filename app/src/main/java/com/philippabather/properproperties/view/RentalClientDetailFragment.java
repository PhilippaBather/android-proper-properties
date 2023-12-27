package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.map.MapUtils;

public class RentalClientDetailFragment extends Fragment implements Style.OnStyleLoaded {
    private Button btnBack;
    private CheckBox cbPropertyFurnished;
    private CheckBox cbPropertyLift;
    private CheckBox cbPropertyParking;
    private CheckBox cbPropertyPets;
    private MapView mapView;
    private TextView tvPropertyBathrooms;
    private TextView tvPropertyBedrooms;
    private TextView tvPropertyDeposit;
    private TextView tvPropertyDescription;
    private TextView tvPropertyMinTen;
    private TextView tvPropertyPrice;
    private TextView tvPropertySize;
    private TextView tvPropertyType;

    private RentalProperty rental;

    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rental_client_detail, container, false);

        assert getArguments() != null;
        rental = getArguments().getParcelable(BUNDLE_ARGUMENT_RENTAL);

        findViews(view);
        setViews();
        setUpMap();
        setClickListeners();

        return view;
    }

    private void findViews(View view) {
        btnBack = view.findViewById(R.id.btn_cancel);
        cbPropertyFurnished = view.findViewById(R.id.cb_property_leasehold);
        cbPropertyLift = view.findViewById(R.id.cb_property_lift);
        cbPropertyParking = view.findViewById(R.id.cb_property_parking);
        cbPropertyPets = view.findViewById(R.id.cb_property_pets);
        mapView = view.findViewById(R.id.mapView);
        tvPropertyBathrooms = view.findViewById(R.id.tv_property_info_bathrooms);
        tvPropertyBedrooms = view.findViewById(R.id.tv_property_info_bedrooms);
        tvPropertyDeposit = view.findViewById(R.id.tv_property_info_deposit);
        tvPropertyDescription = view.findViewById(R.id.tv_property_info_description);
        tvPropertyMinTen = view.findViewById(R.id.tv_property_info_min_tenancy);
        tvPropertyPrice = view.findViewById(R.id.tv_property_info_price);
        tvPropertySize = view.findViewById(R.id.tv_property_info_size);
        tvPropertyType = view.findViewById(R.id.tv_property_info_type);
    }

    private void setViews() {
        cbPropertyFurnished.setChecked(rental.isFurnished());
        cbPropertyLift.setChecked(rental.isLift());
        cbPropertyParking.setChecked(rental.isParking());
        cbPropertyPets.setChecked(rental.isPetFriendly());
        tvPropertyBathrooms.setText(String.valueOf(rental.getNumBathrooms()));
        tvPropertyBedrooms.setText(String.valueOf(rental.getNumBedrooms()));
        tvPropertyDeposit.setText(String.valueOf(rental.getDeposit()));
        tvPropertyDescription.setText(rental.getDescription());
        tvPropertyMinTen .setText(String.valueOf(rental.getMinTenancy()));
        tvPropertyPrice.setText(String.valueOf(rental.getRentPerMonth()));
        tvPropertySize.setText(String.valueOf(rental.getMetresSqr()));
        tvPropertyType.setText(String.valueOf(rental.getPropertyType()));
    }

    private void setUpMap() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        MapUtils.addMarker(pointAnnotationManager, marker, rental.getLatitude(), rental.getLongitude());
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        MapUtils.setCameraPositionAndZoom(mapView);
    }

    private void setClickListeners() {
        btnBack.setOnClickListener(this::goBackToPropertyListView);
    }

    private void goBackToPropertyListView(View view) {
        Intent intent = new Intent(view.getContext(), PropertyListView.class);
        startActivity(intent);
    }
}