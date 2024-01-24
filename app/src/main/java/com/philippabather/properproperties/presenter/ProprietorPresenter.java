package com.philippabather.properproperties.presenter;

import com.philippabather.properproperties.contract.ProprietorContract;
import com.philippabather.properproperties.domain.Proprietor;
import com.philippabather.properproperties.model.ProprietorModel;
import com.philippabather.properproperties.view.OwnerPropertyView;

/**
 * ProprietorPresenter - presenter para los datos de un proprietario.
 *
 * @author Philippa Bather
 */
public class ProprietorPresenter implements ProprietorContract.Presenter, ProprietorContract.Model.OnLoadProprietorListener {

    private final ProprietorModel model;
    private final OwnerPropertyView view;

    public ProprietorPresenter(OwnerPropertyView view) {
        this.view = view;
        this.model = new ProprietorModel();
    }

    @Override
    public void onLoadProprietorSuccess(Proprietor proprietor) {
        view.getProprietor(proprietor);
    }

    @Override
    public void onLoadProprietorError(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void loadProprietor(String token, String username) {
        model.loadProprietorByUsername(this, token, username);
    }
}
