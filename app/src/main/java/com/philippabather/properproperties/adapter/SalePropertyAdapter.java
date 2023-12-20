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
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.List;

public class SalePropertyAdapter extends RecyclerView.Adapter<SalePropertyHolder> {

    private List<SaleProperty> properties;

    public SalePropertyAdapter(List<SaleProperty> properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public SalePropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new SalePropertyHolder(view, properties);
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
    }
    @Override
    public int getItemCount() {
        return properties.size();
    }
}
