package com.philippabather.properproperties.adapter;

import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_SALE_ID;

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
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.view.PropertyDetailView;

import java.util.List;

public class SalePropertyHolder extends RecyclerView.ViewHolder {

    private final View parentView;
    protected CardView cvPropertyItem;
    protected ImageButton ibPropertyFavourite;
    protected ImageButton ibPropertyContact;
    protected ImageView ivPropertyImage;
    protected TextView tvPropertyTitle;
    protected TextView tvPropertyPrice;
    protected TextView tvPropertyOverview; // detalles principales
    protected TextView tvPropertyDescription;

    public SalePropertyHolder(@NonNull View view, List<SaleProperty> properties) {
        super(view);
        this.parentView = view;

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

    public void goToPropertyDetailsActivity(List<SaleProperty> properties) {
        SaleProperty currSaleProperty = getCurrentProperty(properties);
        Intent intent = new Intent(parentView.getContext(), PropertyDetailView.class);
        String id = String.valueOf(currSaleProperty.getId());
        intent.putExtra(INTENT_EXTRA_SALE_ID, id);
        parentView.getContext().startActivity(intent);
    }

    private SaleProperty getCurrentProperty(List<SaleProperty> properties) {
        int currPosition = getAdapterPosition();
        return properties.get(currPosition);
    }

    private void contactProprietor(View view, List<SaleProperty> properties) {
        // TODO
        Toast.makeText(parentView.getContext(), "Contacted owner", Toast.LENGTH_LONG).show();
    }

    private void addToFavourites(View view, List<SaleProperty> properties) {
        // TODO
        Toast.makeText(parentView.getContext(), "Added as favourite", Toast.LENGTH_LONG).show();
    }

}
