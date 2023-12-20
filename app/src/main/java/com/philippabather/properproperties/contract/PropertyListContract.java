package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.List;

public interface PropertyListContract {

    interface Model {
        interface OnLoadPropertiesListener {
            void onLoadRentalPropertiesSuccess(List<RentalProperty> properties);
            void onLoadSalePropertiesSuccess(List<SaleProperty> properties);
            void onLoadPropertiesError(String msg);
        }

        void loadRentalProperties(OnLoadPropertiesListener listener);
        void loadSaleProperties(OnLoadPropertiesListener listener);

        // TODO - updatePropertyInPropertyHolder() for future functionality

    }

    interface View {
        void listRentalProperties(List<RentalProperty> properties);
        void listSaleProperties(List<SaleProperty> properties);
        void showMessage(String msg);
    }

    interface Presenter {
        void loadRentalProperties();
        void loadSaleProperties();

    }
}
