package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_RENTAL_ID;
import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_SALE;

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
import com.philippabather.properproperties.domain.SaleFavourite;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.map.MapUtils;

/**
 * SaleClientDetailFragment - el fragmento para mostrar las detalles de un inmueble para vender
 * seleccionado por el usuario.
 *
 * @author Philippa Bather
 */
public class SaleClientDetailFragment extends Fragment implements Style.OnStyleLoaded {

    private Button btnBack;
    private Button btnSave;
    private CheckBox cbPropertyLeasehold;
    private CheckBox cbPropertyLift;
    private CheckBox cbPropertyParking;
    private EditText etClientComments;
    private MapView mapView;
    private TextView tvPropertyBathrooms;
    private TextView tvPropertyBedrooms;
    private TextView tvPropertyDescription;
    private TextView tvPropertyPrice;
    private TextView tvPropertySize;
    private TextView tvPropertyType;

    private SaleProperty sale;
    private long saleId;
    private AppLocalDB localDB;
    private PointAnnotationManager pointAnnotationManager; // MapBox libraries - for annotating the map


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale_client_detail, container, false);
        localDB = DBHelperMethods.getConnection(view.getContext());

        assert getArguments() != null;
        sale = getArguments().getParcelable(BUNDLE_ARGUMENT_SALE);
        saleId = getArguments().getLong(BUNDLE_ARGUMENT_RENTAL_ID);

        findViews(view);
        setViews();
        setUpMap();
        setClickListeners();

        return view;
    }

    private void findViews(View view) {
        btnBack = view.findViewById(R.id.btn_cancel);
        btnSave = view.findViewById(R.id.btn_save_comment);
        cbPropertyLeasehold = view.findViewById(R.id.cb_property_leasehold);
        cbPropertyLift = view.findViewById(R.id.cb_property_lift);
        cbPropertyParking = view.findViewById(R.id.cb_property_parking);
        etClientComments = view.findViewById(R.id.et_client_comments);
        mapView = view.findViewById(R.id.mapView);
        tvPropertyBathrooms = view.findViewById(R.id.tv_property_info_bathrooms);
        tvPropertyBedrooms = view.findViewById(R.id.tv_property_info_bedrooms);
        tvPropertyDescription = view.findViewById(R.id.tv_property_info_description);
        tvPropertyPrice = view.findViewById(R.id.tv_property_info_price);
        tvPropertySize = view.findViewById(R.id.tv_property_info_size);
        tvPropertyType = view.findViewById(R.id.tv_property_info_type);
    }

    private void setViews() {
        cbPropertyLeasehold.setChecked(sale.isLeasehold());
        cbPropertyLift.setChecked(sale.isLift());
        cbPropertyParking.setChecked(sale.isParking());
        tvPropertyBathrooms.setText(String.valueOf(sale.getNumBathrooms()));
        tvPropertyBedrooms.setText(String.valueOf(sale.getNumBedrooms()));
        tvPropertyDescription.setText(sale.getDescription());
        tvPropertyPrice.setText(String.valueOf(sale.getPrice()));
        tvPropertySize.setText(String.valueOf(sale.getMetresSqr()));
        tvPropertyType.setText(String.valueOf(sale.getPropertyType()));

        handleCommentUpload();
    }

    private void setUpMap() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = MapUtils.initializePointAnnotationManager(mapView);
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker_view);
        MapUtils.addMarker(pointAnnotationManager, marker, sale.getLatitude(), sale.getLongitude());
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
        RentalFavourite favourite = localDB.rentalPropertyDao().getFavouriteByRentalPropertyId(saleId);
        if (favourite != null) {
            // actualizar
            localDB.salePropertyDao().updateFavouriteByRentalPropertyId(saleId, comment);
        } else {
            // añadir
            SaleFavourite newFavourite = new SaleFavourite(saleId);
            newFavourite.setComment(comment);
            localDB.salePropertyDao().insert(newFavourite);
        }
    }

    private void handleCommentUpload() {
        SaleFavourite favourite = localDB.salePropertyDao().getFavouriteBySalePropertyId(saleId);
        etClientComments.setText(favourite.getComment());
    }
}