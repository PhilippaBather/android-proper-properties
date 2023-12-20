package com.philippabather.properproperties.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.presenter.PropertyListPresenter;

import java.util.List;

public class PropertyHolder extends RecyclerView.ViewHolder {

    private List<Property> properties;

    private View parentView;

    private final PropertyListPresenter presenter;

    public PropertyHolder(@NonNull View view, List<Property> properties, PropertyListPresenter presenter) {
        super(view);
        this.parentView = view;
        this.presenter = presenter;

        // TODO - find text views and buttons by id once created
    }

    // TODO
    public void goToPropertyDetailsActivity(List<Property> properties) {
        Property currProperty = getCurrentProperty(properties);
        // TODO - placeholder
//        Intent intent = new Intent(parentView.getContext(), PropertyDetailsView.class);
//        String id = String.valueOf(currProperty.getId());
//        parentView.getContext().startActivity(intent);
    }

    private Property getCurrentProperty(List<Property> properties) {
        int currPosition = getAdapterPosition();
        return properties.get(currPosition);
    }
}
