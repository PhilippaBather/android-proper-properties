package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
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
    public void loadRentalProperties() {
        model.loadRentalProperties(this);
    }

    @Override
    public void loadSaleProperties() { model.loadSaleProperties(this);

    }

    @Override
    public void onLoadRentalPropertiesSuccess(List<RentalProperty> properties) {
        view.listRentalProperties(properties);
    }

    @Override
    public void onLoadSalePropertiesSuccess(List<SaleProperty> properties) {
        view.listSaleProperties(properties);
    }

    @Override
    public void onLoadPropertiesError(String msg) {
        view.showMessage(msg);
    }
}
