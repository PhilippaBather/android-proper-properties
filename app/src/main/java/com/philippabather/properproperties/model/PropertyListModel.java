package com.philippabather.properproperties.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.db.AppLocalDB;
import com.philippabather.properproperties.db.DBHelperMethods;
import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.SaleFavourite;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * PropertyListModel - model para manejar comunicación con el base de datos para conseguir datos de
 * listas de inmuebles de alquiler ('rental') o para vender ('sale').
 *
 * @author Philippa Bather
 */
public class PropertyListModel implements PropertyListContract.Model {

    private final PropertyApiInterface api;
    private final AppLocalDB localDB;

    public PropertyListModel(Context context) {
        this.api = PropertyApi.buildInstance();
        this.localDB = DBHelperMethods.getConnection(context);
    }

    @Override
    public void loadRentalProperties(OnLoadPropertiesListener listener) {
        Call<Set<RentalProperty>> callProperties = api.getRentalProperties();

        callProperties.enqueue(new Callback<Set<RentalProperty>>() {
            @Override
            public void onResponse(@NonNull Call<Set<RentalProperty>> call, @NonNull Response<Set<RentalProperty>> response) {
                Set<RentalProperty> properties = response.body();
                assert properties != null;
                List<RentalProperty> rentalPropertyList = new ArrayList<>(properties);
                for (RentalProperty rentalProperty :
                        rentalPropertyList) {
                    Log.e("rental-property", rentalProperty.toString());
                }
                listener.onLoadRentalPropertiesSuccess(rentalPropertyList);
            }

            @Override
            public void onFailure(@NonNull Call<Set<RentalProperty>> call, @NonNull Throwable t) {
                Log.e("getProperties", Objects.requireNonNull(t.getMessage()));
                listener.onLoadPropertiesError("Se ha producido un error al connectar con el servidor.");
            }
        });
    }

    @Override
    public void loadSaleProperties(OnLoadPropertiesListener listener) {
        Call<Set<SaleProperty>> callProperties = api.getSaleProperties();

        callProperties.enqueue(new Callback<Set<SaleProperty>>() {
            @Override
            public void onResponse(@NonNull Call<Set<SaleProperty>> call, @NonNull Response<Set<SaleProperty>> response) {
                Set<SaleProperty> properties = response.body();
                assert properties != null;
                List<SaleProperty> salePropertyList = new ArrayList<>(properties);
                for (SaleProperty rentalProperty :
                        salePropertyList) {
                    Log.e("sale-property", rentalProperty.toString());
                }
                listener.onLoadSalePropertiesSuccess(salePropertyList);
            }

            @Override
            public void onFailure(@NonNull Call<Set<SaleProperty>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadRentalFavourites(OnLoadFavouritesListener listener) {
        List<RentalFavourite> favourites = localDB.rentalPropertyDao().getAll();
        listener.onLoadRentalFavouritesSuccess(favourites);
    }

    @Override
    public void loadSaleFavourites(OnLoadFavouritesListener listener) {
        List<SaleFavourite> favourites = localDB.salePropertyDao().getAll();
        listener.onLoadSaleFavouritesSuccess(favourites);
    }
}
