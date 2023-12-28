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

/**
 * SalePropertyAdapter - adaptador para manejar el RecyclerView para inmuebles vendidos.
 *
 * @author Philippa Bather
 */
public class SalePropertyAdapter extends RecyclerView.Adapter<SalePropertyHolder> {

    private final List<SaleProperty> properties;
    private final List<SaleFavourite> favourites;
    private final Role role;
    private long proprietorId;

    public SalePropertyAdapter(List<SaleProperty> properties, List<SaleFavourite> favourites, Role role, long proprietorId) {
        this.properties = properties;
        this.favourites = favourites;
        this.role = role;
        this.proprietorId = proprietorId;
    }

    @NonNull
    @Override
    public SalePropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new SalePropertyHolder(view, properties, role, proprietorId);
    }

    @Override
    public void onBindViewHolder(@NonNull SalePropertyHolder salePropertyHolder, int position) {
        SaleProperty saleProperty = properties.get(position);
        salePropertyHolder.ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        salePropertyHolder.tvPropertyTitle.setText(String.format("%s", saleProperty.getPropertyType()));
        salePropertyHolder.tvPropertyPrice.setText(String.format("%.2f %c", saleProperty.getPrice(), euro));
        boolean isLift = saleProperty.isLift();
        int numBathrooms = saleProperty.getNumBathrooms();
        int numBedrooms = saleProperty.getNumBedrooms();
        salePropertyHolder.tvPropertyDescription.setText(saleProperty.getDescription());
        String overview = String.format("Bedrooms: %d\tBathrooms: %d\tlift: %c", numBedrooms, numBathrooms, (isLift ?  tick : cross));
        salePropertyHolder.tvPropertyOverview.setText(overview);

        if (favourites != null) {
            setFavourites(salePropertyHolder, saleProperty);
        }
    }

    /**
     * Estable los favoritos.
     * @param rentalPropertyHolder - holder
     * @param sale - inmueble para vender
     */
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
