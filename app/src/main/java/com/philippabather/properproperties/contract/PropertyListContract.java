package com.philippabather.properproperties.contract;

import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleFavourite;
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

        interface OnLoadFavouritesListener {
            void onLoadRentalFavouritesSuccess(List<RentalFavourite> favourites);
            void onLoadSaleFavouritesSuccess(List<SaleFavourite> favourites);
            void onLoadFavouritesError(String msg);
        }
        void loadRentalFavourites(OnLoadFavouritesListener listener);
        void loadSaleFavourites(OnLoadFavouritesListener listener);
    }

    interface View {
        void listRentalProperties(List<RentalProperty> properties);
        void listSaleProperties(List<SaleProperty> properties);
        void listFavouriteRentals(List<RentalFavourite> properties);
        void listFavouriteSales(List<SaleFavourite> properties);
        void showMessage(String msg);
    }

    interface Presenter {
        void loadRentalProperties();
        void loadSaleProperties();
        void loadFavouriteRentals();
        void loadFavouriteSales();
    }
}
