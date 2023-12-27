package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

public interface OwnerContract {

    public interface Model {
        interface OnLoadPropertiesListener {
            void onLoadProprietorSuccess(Proprietor proprietor);
            void onLoadProprietorError(String msg);
        }

        void loadProprietor(OwnerContract.Model.OnLoadPropertiesListener listener, long userId);
    }

    public interface View {
        void getProprietor(Proprietor proprietor);
        void showMessage(String msg);
    }

    public interface Presenter {
        void loadProprietor(long proprietorId);

    }
}
