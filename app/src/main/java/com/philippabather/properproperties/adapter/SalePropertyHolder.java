package com.philippabather.properproperties.adapter;

import static com.philippabather.properproperties.R.color.barbie_pink;
import static com.philippabather.properproperties.R.color.mint;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPERTY_STATUS;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;
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
import com.philippabather.properproperties.db.AppLocalDB;
import com.philippabather.properproperties.db.DBHelperMethods;
import com.philippabather.properproperties.domain.Role;
import com.philippabather.properproperties.domain.SaleFavourite;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.view.PropertyDetailView;
import com.philippabather.properproperties.view.PropertyUpdateView;

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

    private final AppLocalDB localDB;
    private long proprietorId;

    public SalePropertyHolder(@NonNull View view, List<SaleProperty> properties,
                              List<SaleFavourite> favourites, Role role, long proprietorId) {
        super(view);
        this.parentView = view;
        this.proprietorId = proprietorId;

        findViews();
        cvPropertyItem.setOnClickListener(v -> goToPropertyDetailsActivity(properties, role));
        ibPropertyContact.setOnClickListener(v -> contactProprietor(properties));
        ibPropertyFavourite.setOnClickListener(v -> addToFavourites(properties));
        localDB = DBHelperMethods.getConnection(view.getContext());
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

    public void goToPropertyDetailsActivity(List<SaleProperty> properties, Role role) {
        SaleProperty currSaleProperty = getCurrentProperty(properties);
        if (role.equals(Role.CLIENT)) {
            Intent intent = new Intent(parentView.getContext(), PropertyDetailView.class);
            String id = String.valueOf(currSaleProperty.getId());
            intent.putExtra(INTENT_EXTRA_SALE_ID, id);
            parentView.getContext().startActivity(intent);
        } else if (role.equals(Role.PROPRIETOR)) {
            Intent intent = new Intent(parentView.getContext(), PropertyUpdateView.class);
            intent.putExtra(INTENT_EXTRA_PROPERTY_STATUS, currSaleProperty.getPropertyStatus().toString());
            intent.putExtra(INTENT_EXTRA_PROPERTY, currSaleProperty);
            intent.putExtra(INTENT_EXTRA_PROPRIETOR_ID, String.valueOf(proprietorId));
            parentView.getContext().startActivity(intent);
        }
    }

    private SaleProperty getCurrentProperty(List<SaleProperty> properties) {
        int currPosition = getAdapterPosition();
        return properties.get(currPosition);
    }

    private void contactProprietor(List<SaleProperty> properties) {
        Toast.makeText(parentView.getContext(), "Contacted owner", Toast.LENGTH_LONG).show();
    }

    private void addToFavourites(List<SaleProperty> properties) {
        SaleProperty sale = getCurrentProperty(properties);
        sale.setFavourite(!sale.isFavourite());
        updateLocalDB(sale);
        updateItemBackgroundOnClick(sale);
    }

    private void updateLocalDB(SaleProperty sale) {
        if (!sale.isFavourite()) {
            SaleFavourite favourite = localDB.salePropertyDao().getFavouriteBySalePropertyId(sale.getId());
            localDB.salePropertyDao().delete(favourite);
        } else {
            SaleFavourite favourite = new SaleFavourite(sale.getId());
            localDB.salePropertyDao().insert(favourite);
        }
    }

    protected void updateItemBackgroundOnClick(SaleProperty saleProperty) {
        if(saleProperty.isFavourite()){
            ibPropertyFavourite.setColorFilter(parentView.getContext().getColor(barbie_pink));
        } else {
            ibPropertyFavourite.setColorFilter(parentView.getContext().getColor(mint));
        }
    }

}
