package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.PropertyDetailContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.model.PropertyDetailModel;
import com.philippabather.properproperties.view.PropertyDetailView;

public class PropertyDetailPresenter implements PropertyDetailContract.Presenter,
        PropertyDetailContract.Model.OnLoadRentalPropertyListener,
        PropertyDetailContract.Model.OnLoadSalePropertyListener {

    private final PropertyDetailView view;
    private final PropertyDetailModel model;

    public PropertyDetailPresenter(PropertyDetailView view) {
        this.view = view;
        this.model = new PropertyDetailModel();
    }

    @Override
    public void onLoadRentalPropertySuccess(RentalProperty rentalProperty) {
        view.listRentalProperty(rentalProperty);
    }

    @Override
    public void onLoadRentalPropertyError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void onLoadSalePropertySuccess(SaleProperty saleProperty) {
        view.listSaleProperty(saleProperty);
    }

    @Override
    public void onLoadSalePropertyError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void loadSelectedRentalProperty(long rentalId) {
        model.loadSelectedRentalProperty(this, rentalId);
    }

    @Override
    public void loadSelectedSaleProperty(long saleId) {
        model.loadSelectedSaleProperty(this, saleId);
    }
}
