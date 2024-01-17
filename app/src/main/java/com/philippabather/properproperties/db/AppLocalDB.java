package com.philippabather.properproperties.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.SaleFavourite;

/**
 * AppLocalDB - clase abstracta contiendeo las definiciones de métodos para el base de datos local (Rooms).
 *
 * @author Philippa Bather
 */
@Database(entities = {RentalFavourite.class, SaleFavourite.class}, version = 5)
public abstract class AppLocalDB extends RoomDatabase {
        public abstract RentalPropertyDao rentalPropertyDao();
        public abstract SalePropertyDao salePropertyDao();

}
