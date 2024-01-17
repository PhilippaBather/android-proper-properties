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
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.List;

/**
 * SalePropertyAdapter - adaptador para manejar el RecyclerView para inmuebles vendidos.
 *
 * @author Philippa Bather
 */
public class SalePropertyAdapter extends RecyclerView.Adapter<SalePropertyHolder> {

    private final List<SaleProperty> properties;
    private final Role role;

    public SalePropertyAdapter(List<SaleProperty> properties, Role role) {
        this.properties = properties;
        this.role = role;
    }

    @NonNull
    @Override
    public SalePropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new SalePropertyHolder(view, properties, role);
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
        String overview = String.format("Bedrooms: %d\t  Bathrooms: %d\t  Lift: %c", numBedrooms, numBathrooms, (isLift ?  tick : cross));
        salePropertyHolder.tvPropertyOverview.setText(overview);

        salePropertyHolder.addFavourites(saleProperty);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
}
