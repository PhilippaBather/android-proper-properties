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
import com.philippabather.properproperties.domain.RentalProperty;

import java.util.List;

public class RentalPropertyAdapter extends RecyclerView.Adapter<RentalPropertyHolder> {

    private List<RentalProperty> properties;

    public RentalPropertyAdapter(List<RentalProperty> properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public RentalPropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new RentalPropertyHolder(view, properties);
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
    }
    @Override
    public int getItemCount() {
        return properties.size();
    }
}
