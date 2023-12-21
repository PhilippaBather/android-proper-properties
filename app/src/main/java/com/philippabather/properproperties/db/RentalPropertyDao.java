package com.philippabather.properproperties.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.philippabather.properproperties.domain.RentalFavourite;

import java.util.List;

@Dao
public interface RentalPropertyDao {

    @Query("SELECT * FROM RentalFavourite")
    List<RentalFavourite> getAll();

    @Query("SELECT * FROM RentalFavourite where rentalPropertyId = :rentalPropertyId")
    RentalFavourite getFavouriteByRentalPropertyId(long rentalPropertyId);

    @Insert
    void insert(RentalFavourite favourite);

    @Delete
    void delete(RentalFavourite favourite);
}
