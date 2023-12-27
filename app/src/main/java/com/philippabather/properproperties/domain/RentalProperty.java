package com.philippabather.properproperties.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalProperty implements Parcelable {

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
    private BigDecimal rentPerMonth;
    private BigDecimal deposit;
    private int minTenancy;
    private boolean isFurnished;
    private boolean isPetFriendly;
    private boolean isFavourite;

    public RentalProperty(long id, PropertyStatus propertyStatus, PropertyType propertyType,
                          double latitude, double longitude, int metresSqr, String description,
                          String availableFrom, LocalDate availableFromLD, int numBedrooms,
                          int numBathrooms, boolean isParking, boolean isLift,
                          BigDecimal rentPerMonth, BigDecimal deposit, int minTenancy,
                          boolean isFurnished, boolean isPetFriendly) {
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
        this.rentPerMonth = rentPerMonth;
        this.deposit = deposit;
        this.minTenancy = minTenancy;
        this.isFurnished = isFurnished;
        this.isPetFriendly = isPetFriendly;
        this.isFavourite = false;
    }

    protected RentalProperty(Parcel in) {
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
        minTenancy = in.readInt();
        isFurnished = in.readByte() != 0;
        isPetFriendly = in.readByte() != 0;
        isFavourite = in.readByte() != 0;
    }

    public static final Creator<RentalProperty> CREATOR = new Creator<RentalProperty>() {
        @Override
        public RentalProperty createFromParcel(Parcel in) {
            return new RentalProperty(in);
        }

        @Override
        public RentalProperty[] newArray(int size) {
            return new RentalProperty[size];
        }
    };

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

    public String getDescription() {
        return description;
    }
    public BigDecimal getRentPerMonth() {
        return rentPerMonth;
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
        parcel.writeInt(minTenancy);
        parcel.writeByte((byte) (isFurnished ? 1 : 0));
        parcel.writeByte((byte) (isPetFriendly ? 1 : 0));
        parcel.writeByte((byte) (isFavourite ? 1 : 0));
    }

    @Override
    public String toString() {
        return "RentalProperty{" +
                "propertyStatus=" + propertyStatus +
                ", propertyType=" + propertyType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", metresSqr=" + metresSqr +
                ", description='" + description + '\'' +
                ", availableFrom='" + availableFrom + '\'' +
                ", availableFromLD=" + availableFromLD +
                ", numBedrooms=" + numBedrooms +
                ", numBathrooms=" + numBathrooms +
                ", isParking=" + isParking +
                ", isLift=" + isLift +
                ", rentPerMonth=" + rentPerMonth +
                ", deposit=" + deposit +
                ", minTenancy=" + minTenancy +
                ", isFurnished=" + isFurnished +
                ", isPetFriendly=" + isPetFriendly +
                '}';
    }
}
