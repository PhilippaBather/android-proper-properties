package com.philippabather.properproperties.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleProperty implements Parcelable {

    private long id;

    private PropertyStatus propertyStatus;

    private PropertyType propertyType;

    private double latitude;

    private double longitude;

    private int metresSqr;

    private String description;

    private String availableFrom; // disponibleDesde
    private LocalDate availableFromLD;
    private int numBedrooms;

    private int numBathrooms;
    private boolean isParking;
    private boolean isLift;
    private BigDecimal price;
    private boolean isLeasehold;
    private String constructionDate;
    private LocalDate constructionDateLD;
    private boolean isFavourite;

    public SaleProperty(long id, PropertyStatus propertyStatus, PropertyType propertyType,
                        double latitude, double longitude, int metresSqr, String description,
                        String availableFrom, LocalDate availableFromLD, int numBedrooms,
                        int numBathrooms, boolean isParking, boolean isLift, BigDecimal price,
                        boolean isLeasehold, String constructionDate, LocalDate constructionDateLD) {
        this.id = id;
        this.propertyStatus = propertyStatus;
        this.propertyType = propertyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metresSqr = metresSqr;
        this.description = description;
        this.availableFrom = availableFrom;
        this.availableFromLD = availableFromLD;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.isParking = isParking;
        this.isLift = isLift;
        this.price = price;
        this.isLeasehold = isLeasehold;
        this.constructionDate = constructionDate;
        this.constructionDateLD = constructionDateLD;
        this.isFavourite = false;
    }

    protected SaleProperty(Parcel in) {
        id = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();
        metresSqr = in.readInt();
        description = in.readString();
        availableFrom = in.readString();
        numBedrooms = in.readInt();
        numBathrooms = in.readInt();
        isParking = in.readByte() != 0;
        isLift = in.readByte() != 0;
        isLeasehold = in.readByte() != 0;
        constructionDate = in.readString();
        isFavourite = in.readByte() != 0;
    }

    public static final Creator<SaleProperty> CREATOR = new Creator<SaleProperty>() {
        @Override
        public SaleProperty createFromParcel(Parcel in) {
            return new SaleProperty(in);
        }

        @Override
        public SaleProperty[] newArray(int size) {
            return new SaleProperty[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(metresSqr);
        parcel.writeString(description);
        parcel.writeString(availableFrom);
        parcel.writeInt(numBedrooms);
        parcel.writeInt(numBathrooms);
        parcel.writeByte((byte) (isParking ? 1 : 0));
        parcel.writeByte((byte) (isLift ? 1 : 0));
        parcel.writeByte((byte) (isLeasehold ? 1 : 0));
        parcel.writeString(constructionDate);
        parcel.writeByte((byte) (isFavourite ? 1 : 0));
    }

    public long getId() {
        return id;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getMetresSqr() {
        return metresSqr;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public boolean isLift() {
        return isLift;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
