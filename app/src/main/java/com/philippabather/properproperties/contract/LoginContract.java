package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

public interface LoginContract {

    interface Model {
        interface OnLoadProprietorListener {

            void onLoadProprietorSuccess(Proprietor proprietor);
            void onLoadProprietorError(String msg);

        }
    }

    interface View {

    }

    interface Presenter {

    }
}
