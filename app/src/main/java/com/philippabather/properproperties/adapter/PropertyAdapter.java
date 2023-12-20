package com.philippabather.properproperties.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.List;
import java.util.Set;

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
        // TODO: update text etc about property

    }
    @Override
    public int getItemCount() {
        return properties.size();
    }
}
