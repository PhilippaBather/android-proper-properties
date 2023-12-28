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
import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.Role;

import java.util.List;

/**
 * RentalPropertyAdapter - adaptador para manejar el RecyclerView para inmuebles de alquiler.
 *
 * @author Philippa Bather
 */
public class RentalPropertyAdapter extends RecyclerView.Adapter<RentalPropertyHolder> {

    private final List<RentalProperty> properties;
    private final List<RentalFavourite> favourites;
    private final Role role;
    private final long proprietorId;

    public RentalPropertyAdapter(List<RentalProperty> properties, List<RentalFavourite> favourites, Role role, long proprietorId) {
        this.properties = properties;
        this.favourites = favourites;
        this.role = role;
        this.proprietorId = proprietorId;
    }

    @NonNull
    @Override
    public RentalPropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new RentalPropertyHolder(view, properties, role, proprietorId);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalPropertyHolder rentalPropertyHolder, int position) {
        // establecer el texto para cada ítem
        RentalProperty rentalProperty = properties.get(position);
        rentalPropertyHolder.ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        rentalPropertyHolder.tvPropertyTitle.setText(String.format("%s : %s", rentalProperty.getPropertyStatus(), rentalProperty.getPropertyType()));
        rentalPropertyHolder.tvPropertyPrice.setText(String.format("%.2f %c p/m", rentalProperty.getRentPerMonth(), euro));
        boolean isLift = rentalProperty.isLift();
        int numBathrooms = rentalProperty.getNumBathrooms();
        int numBedrooms = rentalProperty.getNumBedrooms();
        rentalPropertyHolder.tvPropertyDescription.setText(rentalProperty.getDescription());
        String overview = String.format("Bedrooms: %d\t  Bathrooms: %d\t  Lift: %c", numBedrooms, numBathrooms, (isLift ?  tick : cross));
        rentalPropertyHolder.tvPropertyOverview.setText(overview);

        if (favourites != null) {
            setFavourites(rentalPropertyHolder, rentalProperty);
        }

    }

    /**
     * Establece los favoritos.
     * @param rentalPropertyHolder - holder
     * @param rental - inmueble de alquiler
     */
    private void setFavourites(RentalPropertyHolder rentalPropertyHolder, RentalProperty rental) {
        long rentalId = rental.getId();
        for (RentalFavourite favourite:
             favourites) {
            boolean isFavourite = rentalId == favourite.getRentalPropertyId();
            rental.setFavourite(isFavourite);
            rentalPropertyHolder.updateImageButtonTint(rental);
        }
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
}
