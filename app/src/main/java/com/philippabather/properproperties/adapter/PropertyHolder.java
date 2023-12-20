package com.philippabather.properproperties.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.List;

public class PropertyHolder extends RecyclerView.ViewHolder {

    private View parentView;
    protected ImageButton ibPropertyFavourite;
    protected ImageButton ibPropertyContact;
    protected ImageView ivPropertyImage;
    protected TextView tvPropertyTitle;
    protected TextView tvPropertyPrice;
    protected TextView tvPropertyOverview; // detalles principales
    protected TextView tvPropertyDescription;

    private final PropertyListPresenter presenter;

    public PropertyHolder(@NonNull View view, List<Property> properties, PropertyListPresenter presenter) {
        super(view);
        this.parentView = view;
        this.presenter = presenter;

        findViews();
        ibPropertyContact.setOnClickListener(v -> contactProprietor(view, properties));
        ibPropertyFavourite.setOnClickListener(v -> addToFavourites(view, properties));
    }

    private void findViews() {
        ibPropertyContact = parentView.findViewById(R.id.img_btn_property_contact);
        ibPropertyFavourite = parentView.findViewById(R.id.img_btn_property_favourite);
        ivPropertyImage = parentView.findViewById(R.id.iv_property_img);
        tvPropertyTitle = parentView.findViewById(R.id.tv_property_title);
        tvPropertyPrice = parentView.findViewById(R.id.tv_property_price);
        tvPropertyOverview = parentView.findViewById(R.id.tv_property_key_details);
        tvPropertyDescription = parentView.findViewById(R.id.tv_property_description);
    }

    // TODO
    public void goToPropertyDetailsActivity(List<Property> properties) {
        Property currProperty = getCurrentProperty(properties);
        // TODO - placeholder
//        Intent intent = new Intent(parentView.getContext(), PropertyDetailsView.class);
//        String id = String.valueOf(currProperty.getId());
//        parentView.getContext().startActivity(intent);
    }

    private Property getCurrentProperty(List<Property> properties) {
        int currPosition = getAdapterPosition();
        return properties.get(currPosition);
    }

    private void contactProprietor(View view, List<Property> properties) {
        // TODO
        Toast.makeText(parentView.getContext(), "Contacted owner", Toast.LENGTH_LONG).show();
    }

    private void addToFavourites(View view, List<Property> properties) {
        // TODO
        Toast.makeText(parentView.getContext(), "Added as favourite", Toast.LENGTH_LONG).show();
    }

}
