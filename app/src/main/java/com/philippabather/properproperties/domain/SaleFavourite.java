package com.philippabather.properproperties.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * SaleFavourite - clase define un favorito inmueble para vender; usado para guardar información
 * en el base de datos local (Rooms) de favoritos.
 *
 * @author Philippa Bather
 */
@Entity
public class SaleFavourite {
    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    @ColumnInfo
    private Long salePropertyId;

    @ColumnInfo
    private String comment;

    public SaleFavourite(@NonNull Long salePropertyId) {
        this.salePropertyId = salePropertyId;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Long getSalePropertyId() {
        return salePropertyId;
    }

    public void setSalePropertyId(@NonNull Long salePropertyId) {
        this.salePropertyId = salePropertyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SaleFavourite{" +
                "id=" + id +
                ", salePropertyId=" + salePropertyId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
