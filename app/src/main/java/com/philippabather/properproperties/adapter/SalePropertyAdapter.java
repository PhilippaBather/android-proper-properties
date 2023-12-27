package com.philippabather.properproperties.adapter;

import static com.philippabather.properproperties.constants.Constants.cross;
import static com.philippabather.properproperties.constants.Constants.euro;
import static com.philippabather.properproperties.constants.Constants.tick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.Role;
import com.philippabather.properproperties.domain.SaleFavourite;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.List;

public class SalePropertyAdapter extends RecyclerView.Adapter<SalePropertyHolder> {

    private final List<SaleProperty> properties;
    private final List<SaleFavourite> favourites;

    private final Role role;

    public SalePropertyAdapter(List<SaleProperty> properties, List<SaleFavourite> favourites, Role role) {
        this.properties = properties;
        this.favourites = favourites;
        this.role = role;
    }

    @NonNull
    @Override
    public SalePropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new SalePropertyHolder(view, properties, favourites, role);
    }

    @Override
    public void onBindViewHolder(@NonNull SalePropertyHolder salePropertyHolder, int position) {
        SaleProperty saleProperty = properties.get(position);
        salePropertyHolder.ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        salePropertyHolder.tvPropertyTitle.setText(String.format("%s", saleProperty.getPropertyType()));
        salePropertyHolder.tvPropertyPrice.setText(String.format("%.2f %c", saleProperty.getPrice(), euro));
        int numBedrooms = saleProperty.getNumBedrooms();
        int metresSqr = saleProperty.getMetresSqr();
        boolean isLift = saleProperty.isLift();
        salePropertyHolder.tvPropertyDescription.setText(saleProperty.getDescription());
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ?  tick : cross));
        salePropertyHolder.tvPropertyOverview.setText(overview);

        if (favourites != null) {
            setFavourites(salePropertyHolder, saleProperty);
        }
    }

    private void setFavourites(SalePropertyHolder rentalPropertyHolder, SaleProperty sale) {
        long rentalId = sale.getId();
        for (SaleFavourite favourite:
                favourites) {
            boolean isFavourite = rentalId == favourite.getSalePropertyId();
            sale.setFavourite(isFavourite);
            rentalPropertyHolder.updateItemBackgroundOnClick(sale);
        }
    }
    @Override
    public int getItemCount() {
        return properties.size();
    }
}
