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

import java.util.List;

public class RentalPropertyAdapter extends RecyclerView.Adapter<RentalPropertyHolder> {

    private final List<RentalProperty> properties;
    private final List<RentalFavourite> favourites;

    public RentalPropertyAdapter(List<RentalProperty> properties, List<RentalFavourite> favourites) {
        this.properties = properties;
        this.favourites = favourites;
    }

    @NonNull
    @Override
    public RentalPropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new RentalPropertyHolder(view, properties, favourites);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalPropertyHolder rentalPropertyHolder, int position) {
        RentalProperty rentalProperty = properties.get(position);
        rentalPropertyHolder.ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        rentalPropertyHolder.tvPropertyTitle.setText(String.format("%s : %s", rentalProperty.getPropertyStatus(), rentalProperty.getPropertyType()));
        rentalPropertyHolder.tvPropertyPrice.setText(String.format("%.2f %c p/m", rentalProperty.getRentPerMonth(), euro));
        int numBedrooms = rentalProperty.getNumBedrooms();
        int metresSqr = rentalProperty.getMetresSqr();
        boolean isLift = rentalProperty.isLift();
        rentalPropertyHolder.tvPropertyDescription.setText(rentalProperty.getDescription());
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ?  tick : cross));
        rentalPropertyHolder.tvPropertyOverview.setText(overview);

        setCardBackgroundAsFavourite(rentalPropertyHolder, rentalProperty);

    }

    private void setCardBackgroundAsFavourite(RentalPropertyHolder rentalPropertyHolder, RentalProperty rental) {
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
