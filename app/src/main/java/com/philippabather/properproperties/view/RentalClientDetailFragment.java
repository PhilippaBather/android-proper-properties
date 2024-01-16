package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL_ID;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.philippabather.properproperties.R;
import com.philippabather.properproperties.db.AppLocalDB;
import com.philippabather.properproperties.db.DBHelperMethods;
import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.map.MapUtils;

/**
 * RentalClientDetailFragment - el fragmento para mostrar las detalles de un inmueble de alquiler
 * seleccionado por el usuario.
 *
 * @author Philippa Bather
 */
public class RentalClientDetailFragment extends Fragment implements Style.OnStyleLoaded {
    private Button btnBack;
    private Button btnSave;
    private CheckBox cbPropertyFurnished;
    private CheckBox cbPropertyLift;
    private CheckBox cbPropertyParking;
    private CheckBox cbPropertyPets;
    private EditText etClientComments;
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
    private long rentalId;
    private AppLocalDB localDB;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rental_client_detail, container, false);
        localDB = DBHelperMethods.getConnection(view.getContext());

        assert getArguments() != null;
        rental = getArguments().getParcelable(BUNDLE_ARGUMENT_RENTAL);
        rentalId = getArguments().getLong(BUNDLE_ARGUMENT_RENTAL_ID);

        findViews(view);
        setViews();
        setUpMap();
        setClickListeners();

        return view;
    }

    private void findViews(View view) {
        btnBack = view.findViewById(R.id.btn_cancel);
        btnSave = view.findViewById(R.id.btn_save_comment);
        cbPropertyFurnished = view.findViewById(R.id.cb_property_leasehold);
        cbPropertyLift = view.findViewById(R.id.cb_property_lift);
        cbPropertyParking = view.findViewById(R.id.cb_property_parking);
        cbPropertyPets = view.findViewById(R.id.cb_property_pets);
        etClientComments = view.findViewById(R.id.et_client_comments);
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
        tvPropertyMinTen.setText(String.valueOf(rental.getMinTenancy()));
        tvPropertyPrice.setText(String.valueOf(rental.getRentPerMonth()));
        tvPropertySize.setText(String.valueOf(rental.getMetresSqr()));
        tvPropertyType.setText(String.valueOf(rental.getPropertyType()));

        handleCommentUpload();
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
        btnSave.setOnClickListener(this::handleSaveComment);
    }

    private void goBackToPropertyListView(View view) {
        Intent intent = new Intent(view.getContext(), PropertyListView.class);
        startActivity(intent);
    }

    private void handleSaveComment(View view) {
        String comment = etClientComments.getText().toString();
        // comprobar si es favorito
        RentalFavourite favourite = localDB.rentalPropertyDao().getFavouriteByRentalPropertyId(rentalId);
        if (favourite != null) {
            // si es un favorito, actualizalo
            localDB.rentalPropertyDao().updateFavouriteByRentalPropertyId(rentalId, comment);
        } else {
            // si no, añadelo al local DB
            RentalFavourite newFavourite = new RentalFavourite(rentalId);
            newFavourite.setComment(comment);
            localDB.rentalPropertyDao().insert(newFavourite);
        }
    }

    private void handleCommentUpload() {
        RentalFavourite favourite = localDB.rentalPropertyDao().getFavouriteByRentalPropertyId(rentalId);
        if (favourite != null) {
            etClientComments.setText(favourite.getComment());
        }
    }
}