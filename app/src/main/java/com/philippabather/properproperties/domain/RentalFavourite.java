package com.philippabather.properproperties.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RentalFavourite - clase define un favorito inmueble de alquiler; usado para guardar información
 * en el base de datos local (Rooms) de favoritos.
 *
 * @author Philippa Bather
 */
@Entity
public class RentalFavourite {

    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    @ColumnInfo
    private Long rentalPropertyId;

    @ColumnInfo
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "RentalFavourite{" +
                "id=" + id +
                ", rentalPropertyId=" + rentalPropertyId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
