package com.philippabather.properproperties.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.presenter.PropertyListPresenter;
import com.philippabather.properproperties.view.PropertyDetailView;

import java.util.List;

public class RentalPropertyHolder extends RecyclerView.ViewHolder {

    private final View parentView;
    protected CardView cvPropertyItem;
    protected ImageButton ibPropertyFavourite;
    protected ImageButton ibPropertyContact;
    protected ImageView ivPropertyImage;
    protected TextView tvPropertyTitle;
    protected TextView tvPropertyPrice;
    protected TextView tvPropertyOverview; // detalles principales
    protected TextView tvPropertyDescription;

    private final PropertyListPresenter presenter;

    public RentalPropertyHolder(@NonNull View view, List<RentalProperty> properties, PropertyListPresenter presenter) {
        super(view);
        this.parentView = view;
        this.presenter = presenter;

        findViews();
        cvPropertyItem.setOnClickListener(v -> goToPropertyDetailsActivity(properties));
        ibPropertyContact.setOnClickListener(v -> contactProprietor(view, properties));
        ibPropertyFavourite.setOnClickListener(v -> addToFavourites(view, properties));
    }

    private void findViews() {
        cvPropertyItem = parentView.findViewById(R.id.cv_property_item);
        ibPropertyContact = parentView.findViewById(R.id.img_btn_property_contact);
        ibPropertyFavourite = parentView.findViewById(R.id.img_btn_property_favourite);
        ivPropertyImage = parentView.findViewById(R.id.iv_property_img);
        tvPropertyTitle = parentView.findViewById(R.id.tv_property_detail_title);
        tvPropertyPrice = parentView.findViewById(R.id.tv_property_price);
        tvPropertyOverview = parentView.findViewById(R.id.tv_property_key_details);
        tvPropertyDescription = parentView.findViewById(R.id.tv_property_description);
    }

    public void goToPropertyDetailsActivity(List<RentalProperty> properties) {
        Toast.makeText(parentView.getContext(), "Card clicked", Toast.LENGTH_LONG).show();
        RentalProperty currRentalProperty = getCurrentProperty(properties);
        Intent intent = new Intent(parentView.getContext(), PropertyDetailView.class);
        String id = String.valueOf(currRentalProperty.getId());
        intent.putExtra("rental_property_id", id);
        parentView.getContext().startActivity(intent);
    }

    private RentalProperty getCurrentProperty(List<RentalProperty> properties) {
        int currPosition = getAdapterPosition();
        return properties.get(currPosition);
    }

    private void contactProprietor(View view, List<RentalProperty> properties) {
        Toast.makeText(parentView.getContext(), "Contacted owner", Toast.LENGTH_LONG).show();
    }

    private void addToFavourites(View view, List<RentalProperty> properties) {
        // TODO
        Toast.makeText(parentView.getContext(), "Added as favourite", Toast.LENGTH_LONG).show();
    }

}
