package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

/**
 * PropertyDetailContract - el contrato para manejar comunicación con el DB para cojer datos de
 * inmuebles de alquiler.
 *
 * @author Philippa Bather
 */
public interface PropertyDetailContract {

    public interface Model {

        interface OnLoadRentalPropertyListener {
            void onLoadRentalPropertySuccess(RentalProperty rentalProperty);
            void onLoadRentalPropertyError(String msg);
        }

        interface OnLoadSalePropertyListener {
            void onLoadSalePropertySuccess(SaleProperty saleProperty);
            void onLoadSalePropertyError(String msg);
        }

        void loadSelectedRentalProperty(OnLoadRentalPropertyListener listener, long rentalId);
        void loadSelectedSaleProperty(OnLoadSalePropertyListener listener, long saleId);
    }

    public interface View {
        void listRentalProperty(RentalProperty rentalProperty);
        void listSaleProperty(SaleProperty saleProperty);
        void showMessage(String msg);
    }

    public interface Presenter {
        void loadSelectedRentalProperty(long rentalId);
        void loadSelectedSaleProperty(long saleId);
    }


}
