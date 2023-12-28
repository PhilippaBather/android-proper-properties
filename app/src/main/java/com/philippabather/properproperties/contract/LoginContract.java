package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Proprietor;

/**
 * LoginContract - contrato temporal para manejar el login de usuario.
 *
 * @author Philippa Bather
 */
public interface LoginContract {
    // TODO - 2 entrega: actualiza el login para implementar funcionalidad segura

    interface Model {
        interface OnLoadProprietorListener {

            void onLoadProprietorSuccess(Proprietor proprietor);
            void onLoadProprietorError(String msg);

        }

        void loadProprietorByUsernameAndPassword(OnLoadProprietorListener listener, String username, String password);
    }

    interface View {
        void getProprietor(Proprietor proprietor);
        void showMessage(String msg);
    }

    interface Presenter {
        void loadProprietorByUsernameAndPassword(String username, String password);
    }
}
