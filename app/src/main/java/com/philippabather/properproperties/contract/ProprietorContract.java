package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

public interface ProprietorContract {

    interface Model {
        interface OnLoadProprietorListener {
            void onLoadProprietorSuccess(Proprietor proprietor);

            void onLoadProprietorError(String msg);
        }

        void loadProprietorByUsername(OnLoadProprietorListener listener, String token, String username );

    }

    interface View {
        void getProprietor(Proprietor proprietor);

        void showMessage(String msg);
    }

    interface Presenter {
        void loadProprietor(String token, String username);
    }

}
