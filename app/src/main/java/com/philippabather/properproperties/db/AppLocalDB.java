package com.philippabather.properproperties.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.philippabather.properproperties.domain.RentalFavourite;
import com.philippabather.properproperties.domain.SaleFavourite;

@Database(entities = {RentalFavourite.class, SaleFavourite.class}, version = 3)
public abstract class AppLocalDB extends RoomDatabase {
        public abstract RentalPropertyDao rentalPropertyDao();
        public abstract SalePropertyDao salePropertyDao();

}
