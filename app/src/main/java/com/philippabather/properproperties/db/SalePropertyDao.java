package com.philippabather.properproperties.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.philippabather.properproperties.domain.SaleFavourite;

import java.util.List;

/**
 * SalePropertyDao - DAO para el base de datos local (Rooms) para inmuebles para vender.
 *
 * @author Philippa Bather
 */
@Dao
public interface SalePropertyDao {

    @Query("SELECT * FROM SaleFavourite")
    List<SaleFavourite> getAll();

    @Query("SELECT * FROM SaleFavourite where salePropertyId = :salePropertyId")
    SaleFavourite getFavouriteBySalePropertyId(long salePropertyId);

    @Insert
    void insert(SaleFavourite favourite);

    @Query("DELETE FROM SaleFavourite WHERE id = :id")
    void deleteFavouriteByFavouriteId(long id);

    @Query("UPDATE SaleFavourite SET comment = :comment WHERE salePropertyId = :salePropertyId")
    void updateFavouriteBySalePropertyId(long salePropertyId, String comment);
}
