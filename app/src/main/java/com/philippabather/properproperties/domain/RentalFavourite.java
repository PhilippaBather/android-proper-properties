package com.philippabather.properproperties.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PropertyFavourite {

    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    @ColumnInfo
    private Long propertyId;

    @NonNull
    @ColumnInfo
    PropertyStatus propertyStatus;

    public PropertyFavourite() {
    }

    public PropertyFavourite(@NonNull Long propertyId, @NonNull PropertyStatus propertyStatus) {
        this.propertyId = propertyId;
        this.propertyStatus = propertyStatus;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(@NonNull Long propertyId) {
        this.propertyId = propertyId;
    }

    @NonNull
    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(@NonNull PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    @Override
    public String toString() {
        return "PropertyFavourite{" +
                "propertyId=" + propertyId +
                ", propertyStatus=" + propertyStatus +
                '}';
    }
}
