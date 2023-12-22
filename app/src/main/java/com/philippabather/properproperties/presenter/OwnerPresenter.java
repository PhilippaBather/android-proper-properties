package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.ManagementContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.model.ManagementModel;
import com.philippabather.properproperties.view.ManagePropertyView;

public class ManagementPresenter implements ManagementContract.Presenter, ManagementContract.Model.OnLoadPropertiesListener {

    private final ManagementModel model;

    private final ManagePropertyView view;

    public ManagementPresenter(ManagePropertyView view) {
        this.view = view;
        this.model = new ManagementModel(view);
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
