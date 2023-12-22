package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.OwnerContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.model.OwnerModel;
import com.philippabather.properproperties.view.OwnerPropertyView;

public class OwnerPresenter implements OwnerContract.Presenter, OwnerContract.Model.OnLoadPropertiesListener {

    private final OwnerModel model;

    private final OwnerPropertyView view;

    public OwnerPresenter(OwnerPropertyView view) {
        this.view = view;
        this.model = new OwnerModel(view);
    }

    @Override
    public void onLoadProprietorSuccess(Proprietor proprietor) {
        view.getProprietor(proprietor);
    }

    @Override
    public void loadProprietor(long proprietorId) {
            model.loadProprietor(this, proprietorId);
    }

    @Override
    public void onLoadProprietorError(String msg) {
        view.showMessage(msg);
    }
}
