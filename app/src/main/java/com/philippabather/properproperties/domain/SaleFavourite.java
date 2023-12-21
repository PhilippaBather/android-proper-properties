package com.philippabather.properproperties.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SaleFavourite {

    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    @ColumnInfo
    private Long salePropertyId;

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

    @Override
    public String toString() {
        return "SaleFavourite{" +
                "id=" + id +
                ", salePropertyId=" + salePropertyId +
                '}';
    }
}
