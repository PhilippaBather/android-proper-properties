package com.philippabather.properproperties.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.philippabather.properproperties.domain.PropertyFavourite;
import com.philippabather.properproperties.domain.RentalProperty;

import java.util.List;

@Dao
public interface PropertyDao {

    @Query("SELECT * FROM propertyfavourite")
    List<PropertyFavourite> getAll();

    @Query("SELECT * FROM propertyfavourite WHERE propertyId = :rentalId")
    PropertyFavourite findByPropertyId(long rentalId);

    @Insert
    void insert(PropertyFavourite favourite);

    @Delete
    void delete(PropertyFavourite favourite);
}
