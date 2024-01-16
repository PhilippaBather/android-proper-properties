package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.PropertyRegistrationContract;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;
import com.philippabather.properproperties.model.PropertyRegistrationModel;
import com.philippabather.properproperties.view.PropertyRegistrationView;

/**
 * PropertyRegistrationPresenter - presenter para la registración de inmuebles.
 *
 * @author Philippa Bather
 */
public class PropertyRegistrationPresenter implements PropertyRegistrationContract.Presenter,
        PropertyRegistrationContract.Model.OnRegisterPropertyListener {

    private final PropertyRegistrationModel model;
    private final PropertyRegistrationView view;

    public PropertyRegistrationPresenter(PropertyRegistrationView view) {
        this.view = view;
        this.model = new PropertyRegistrationModel();
    }

    @Override
    public void createNewRentalProperty(String token, long proprietorId, RentalProperty rental) {
        model.createNewRentalProperty(this, token, proprietorId, rental);
    }

    @Override
    public void createNewSaleProperty(String token, long proprietorId, SaleProperty sale) {
        model.createNewSaleProperty(this, token, proprietorId, sale);
    }

    @Override
    public void onRegisterRentalPropertySuccess(RentalProperty rental) {
        view.showMessage(rental.toString());
    }

    @Override
    public void onRegisterSalePropertySuccess(SaleProperty sale) {
        view.showMessage(sale.toString());
    }

    @Override
    public void onRegisterPropertyError(String msg) {
        view.showMessage(msg);
    }
}
