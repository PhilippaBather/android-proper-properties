package com.philippabather.properproperties.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.philippabather.properproperties.domain.RentalFavourite;

import java.util.List;

/**
 * RentalPropertyDao - DAO para el base de datos local (Rooms) para inmuebles de alquiler.
 *
 * @author Philippa Bather
 */
@Dao
public interface RentalPropertyDao {

    @Query("SELECT * FROM RentalFavourite")
    List<RentalFavourite> getAll();

    @Query("SELECT * FROM RentalFavourite WHERE rentalPropertyId = :rentalPropertyId")
    RentalFavourite getFavouriteByRentalPropertyId(long rentalPropertyId);

    @Insert
    void insert(RentalFavourite favourite);

    @Query("DELETE FROM RentalFavourite WHERE id = :id")
    void deleteFavouriteByFavouriteId(long id);

    @Query("UPDATE RentalFavourite SET comment = :comment WHERE rentalPropertyId = :rentalPropertyId")
    void updateFavouriteByRentalPropertyId(long rentalPropertyId, String comment);
}
