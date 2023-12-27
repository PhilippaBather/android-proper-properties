package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.PropertyStatus;
import com.philippabather.properproperties.domain.PropertyType;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

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

        void deleteRentalProperty(OnDeletePropertyListener listener, long propertyId);
        void deleteSaleProperty(OnDeletePropertyListener listener, long propertyId);
        void updateRentalProperty(OnUpdatePropertyListener listener, long propertyId, RentalProperty rentalProperty);
        void updateSaleProperty(OnUpdatePropertyListener listener, long propertyId, SaleProperty saleProperty);

    }

    interface View {
        void showMessage(String msg);

    }

    interface Presenter {
        void deleteSelectedProperty(long propertyId, PropertyStatus type); // to do an if_else of switch according to request

        void updateRentalProperty(long propertyId, RentalProperty rentalProperty);
        void updateSaleProperty(long propertyId, SaleProperty saleProperty);
    }

}
