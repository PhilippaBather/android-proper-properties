package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.Property;

import java.util.List;

public interface PropertyListContract {

    interface Model {
        interface OnLoadPropertiesListener {
            void onLoadPropertiesSuccess(List<Property> properties);
            void onLoadPropertiesError(String msg);
        }

        void loadAllProperties(OnLoadPropertiesListener listener);

        // TODO - updatePropertyInPropertyHolder() for future functionality

    }

    interface View {
        void listProperties(List<Property> properties);
        void showMessage(String msg);
    }

    interface Presenter {
        void loadAllProperties();
        // TODO
//        void loadAllRentalProperties();
//        void loadAllSaleProperties();

    }
}
