package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

/**
 * OwnerContract - el contrato para manejar comunicación con el DB para cojer datos de un
 * propietario ('owner').
 *
 * @Philippa Bather
 */
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
