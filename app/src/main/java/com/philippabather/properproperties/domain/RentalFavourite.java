package com.philippabather.properproperties.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RentalFavourite {

    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    @ColumnInfo
    private Long rentalPropertyId;

    public RentalFavourite() {
    }

    public RentalFavourite(@NonNull Long rentalPropertyId) {
        this.rentalPropertyId = rentalPropertyId;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Long getRentalPropertyId() {
        return rentalPropertyId;
    }

    public void setRentalPropertyId(@NonNull Long rentalPropertyId) {
        this.rentalPropertyId = rentalPropertyId;
    }

    @Override
    public String toString() {
        return "RentalFavourite{" +
                "id=" + id +
                ", rentalPropertyId=" + rentalPropertyId +
                '}';
    }
}
