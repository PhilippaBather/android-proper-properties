package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.PropertyUpdateContract;
import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.model.PropertyUpdateModel;
import com.philippabather.properproperties.view.PropertyUpdateView;

/**
 * PropertyUpdatePresenter - presenter para las actualizaciones de inmuebles.
 *
 * @author Philippa Bather
 */
public class PropertyUpdatePresenter implements PropertyUpdateContract.Presenter,
        PropertyUpdateContract.Model.OnDeletePropertyListener,
        PropertyUpdateContract.Model.OnUpdatePropertyListener {

    private final PropertyUpdateView view;

    private final PropertyUpdateModel model;

    public PropertyUpdatePresenter(PropertyUpdateView view) {
        this.view = view;
        this.model = new PropertyUpdateModel();
    }

    @Override
    public void onDeleteRentalPropertySuccess() {
        view.showMessage("Property Deleted");
    }

    @Override
    public void onDeleteSalePropertySuccess() {
        view.showMessage("Property Deleted");
    }

    @Override
    public void onDeletePropertyError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void onUpdateRentalPropertySuccess(RentalProperty rentalProperty) {
        view.showMessage("Updated: " + rentalProperty.getId());

    }

    @Override
    public void onUpdateSalePropertySuccess(SaleProperty saleProperty) {
        view.showMessage("Updated: " + saleProperty.getId());
    }

    @Override
    public void onUpdatePropertyError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void deleteSelectedProperty(String token, long propertyId, PropertyStatus status) {
        switch (status) {
            case RENTAL -> model.deleteRentalProperty(this, token, propertyId);
            case SALE -> model.deleteSaleProperty(this, token, propertyId);
        }
    }

    @Override
    public void updateRentalProperty(String token, long propertyId, RentalProperty rentalProperty) {
        model.updateRentalProperty(this, token, propertyId, rentalProperty);
    }

    @Override
    public void updateSaleProperty(String token, long propertyId, SaleProperty saleProperty) {
        model.updateSaleProperty(this, token, propertyId, saleProperty);
    }
}
