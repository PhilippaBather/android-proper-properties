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
import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyHolder> {

    private List<Property> properties;
    private final PropertyListPresenter presenter;

    public PropertyAdapter(List<Property> properties, PropertyListPresenter presenter) {
        this.properties = properties;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public PropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_property_item, parent, false);
        return new PropertyHolder(view, properties, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyHolder propertyHolder, int position) {
        Property property = properties.get(position);
        propertyHolder.ivPropertyImage.setBackgroundResource(R.drawable.house_placeholder_img);
        propertyHolder.tvPropertyTitle.setText("Placeholder - A Great Property");
        propertyHolder.tvPropertyPrice.setText(String.format("555.00 %c", euro));
        int numBedrooms = property.getNumBedrooms();
        int metresSqr = property.getMetresSqr();
        boolean isLift = property.isLift();
        String overview = String.format("Bedrooms: %d\tm2: %d\tlift: %c", numBedrooms, metresSqr, (isLift ?  tick : cross));
        propertyHolder.tvPropertyOverview.setText(overview);
    }
    @Override
    public int getItemCount() {
        return properties.size();
    }
}
