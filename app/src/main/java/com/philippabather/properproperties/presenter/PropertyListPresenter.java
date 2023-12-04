package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.Property;
import com.philippabather.properproperties.model.PropertyListModel;
import com.philippabather.properproperties.view.PropertyListView;

import java.util.List;

public class PropertyListPresenter implements PropertyListContract.Presenter, PropertyListContract.Model.OnLoadPropertiesListener {

    private final PropertyListModel model;
    private final PropertyListView view;

    public PropertyListPresenter(PropertyListView view) {
        this.view = view;
        this.model = new PropertyListModel();
    }

    @Override
    public void loadAllProperties() {
        model.loadAllProperties(this);
    }

    @Override
    public void onLoadPropertiesSuccess(List<Property> properties) {
        view.listProperties(properties);
    }

    @Override
    public void onLoadPropertiesError(String msg) {
        view.showMessage(msg);
    }
}
