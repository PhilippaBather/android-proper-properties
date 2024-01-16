package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

public class ProprietorContract {

    public interface Model {
        interface OnLoadProprietorListener {
            void onLoadProprietorSuccess(Proprietor proprietor);

            void onLoadProprietorError(String msg);
        }

        void loadProprietorByUsername(OnLoadProprietorListener listener, String token, String username );

    }

    public interface View {
        void getProprietor(Proprietor proprietor);

        void showMessage(String msg);
    }

    public interface Presenter {
        void loadProprietor(String token, String username);
    }


}
