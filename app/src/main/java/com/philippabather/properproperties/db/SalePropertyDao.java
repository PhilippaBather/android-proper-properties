package com.philippabather.properproperties.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.philippabather.properproperties.domain.SaleFavourite;

import java.util.List;

@Dao
public interface SalePropertyDao {

    @Query("SELECT * FROM SaleFavourite")
    List<SaleFavourite> getAll();

    @Query("SELECT * FROM SaleFavourite where salePropertyId = :salePropertyId")
    SaleFavourite getFavouriteBySalePropertyId(long salePropertyId);

    @Insert
    void insert(SaleFavourite favourite);

    @Delete
    void delete(SaleFavourite favourite);
}
