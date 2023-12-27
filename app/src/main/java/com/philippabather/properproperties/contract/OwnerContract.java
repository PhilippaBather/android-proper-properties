package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

public interface OwnerContract {

    public interface Model {
        interface OnLoadProprietorListener {
            void onLoadProprietorSuccess(Proprietor proprietor);
            void onLoadProprietorError(String msg);
        }

        void loadProprietor(OnLoadProprietorListener listener, long userId);
    }

    public interface View {
        void getProprietor(Proprietor proprietor);
        void showMessage(String msg);
    }

    public interface Presenter {
        void loadProprietorById(long proprietorId);

    }
}
