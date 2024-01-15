package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

/**
 * PropertyRegistrationContract - el contrato para manejar comunicación con el DB para registrar datos
 * de inmuebles de alquiler ('rental') y para vender ('sale').
 *
 * @author Philippa Bather
 */
public interface PropertyRegistrationContract {

    interface Model {
        interface OnRegisterPropertyListener {
            void onRegisterRentalPropertySuccess(RentalProperty rental);
            void onRegisterSalePropertySuccess(SaleProperty sale);
            void onRegisterPropertyError(String msg);
        }

        void createNewRentalProperty(OnRegisterPropertyListener listener, String token, long proprietorId, RentalProperty rental);
        void createNewSaleProperty(OnRegisterPropertyListener listener, String token, long proprietorId, SaleProperty sale);
    }

    interface View {
        void showMessage(String msg);
    }

    interface Presenter {
        void createNewRentalProperty(String token, long proprietorId, RentalProperty rental);
        void createNewSaleProperty(String token, long proprietorId, SaleProperty sale);
    }
}
