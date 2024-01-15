package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

/**
 * PropertyUpdateContract -  el contrato para manejar comunicación con el DB para actualizar y
 * eliminar datos de inmuebles  de alquiler ('rental') y para vender ('sale').
 *
 * @author Philippa
 */

public interface PropertyUpdateContract {

    interface Model {

        interface OnDeletePropertyListener {
            void onDeleteRentalPropertySuccess();
            void onDeleteSalePropertySuccess();
            void onDeletePropertyError(String msg);
        }

        interface OnUpdatePropertyListener {
            void onUpdateRentalPropertySuccess(RentalProperty rentalProperty);
            void onUpdateSalePropertySuccess(SaleProperty saleProperty);
            void onUpdatePropertyError(String msg);
        }

        void deleteRentalProperty(OnDeletePropertyListener listener, String token, long propertyId);
        void deleteSaleProperty(OnDeletePropertyListener listener, String token, long propertyId);
        void updateRentalProperty(OnUpdatePropertyListener listener, String token, long propertyId, RentalProperty rentalProperty);
        void updateSaleProperty(OnUpdatePropertyListener listener, String token, long propertyId, SaleProperty saleProperty);

    }

    interface View {
        void showMessage(String msg);

    }

    interface Presenter {
        void deleteSelectedProperty(String token, long propertyId, PropertyStatus type);
        void updateRentalProperty(String token, long propertyId, RentalProperty rentalProperty);
        void updateSaleProperty(String token, long propertyId, SaleProperty saleProperty);
    }

}
