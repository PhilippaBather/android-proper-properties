package com.philippabather.properproperties.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

/**
 * Rental Property - la clase define un inmueble de alquiler; implementa Parcelable.
 *
 * @author Philippa Bather
 */
public class RentalProperty implements Parcelable {

    private long id;
    private PropertyStatus propertyStatus;
    private PropertyType propertyType;
    private double latitude;
    private double longitude;
    private int metresSqr;
    private String description;
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

    public RentalProperty(PropertyStatus propertyStatus, PropertyType propertyType,
                          double latitude, double longitude, int metresSqr, String description,
                          int numBedrooms, int numBathrooms, boolean isParking, boolean isLift,
                          BigDecimal rentPerMonth, BigDecimal deposit, int minTenancy,
                          boolean isFurnished, boolean isPetFriendly) {
        this.propertyStatus = propertyStatus;
        this.propertyType = propertyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metresSqr = metresSqr;
        this.description = description;
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

    public RentalProperty(long id, PropertyStatus propertyStatus, PropertyType propertyType, double latitude, double longitude, int metresSqr, String description, int numBedrooms, int numBathrooms, boolean isParking, boolean isLift, BigDecimal rentPerMonth, BigDecimal deposit, int minTenancy, boolean isFurnished, boolean isPetFriendly, boolean isFavourite) {
        this(propertyStatus, propertyType, latitude, longitude, metresSqr, description, numBedrooms, numBathrooms, isParking, isLift, rentPerMonth, deposit, minTenancy, isFurnished, isPetFriendly);
        this.id = id;
    }

    protected RentalProperty(Parcel in) {
        id = in.readLong();
        propertyStatus = PropertyStatus.valueOf(in.readString());
        propertyType = PropertyType.valueOf(in.readString());
        latitude = in.readDouble();
        longitude = in.readDouble();
        metresSqr = in.readInt();
        description = in.readString();
        numBedrooms = in.readInt();
        numBathrooms = in.readInt();
        isParking = in.readByte() != 0;
        isLift = in.readByte() != 0;
        rentPerMonth = new BigDecimal(in.readString());
        deposit = new BigDecimal(in.readString());
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

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMetresSqr() {
        return metresSqr;
    }

    public void setMetresSqr(int metresSqr) {
        this.metresSqr = metresSqr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(int numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public boolean isLift() {
        return isLift;
    }

    public void setLift(boolean lift) {
        isLift = lift;
    }

    public BigDecimal getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(BigDecimal rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public int getMinTenancy() {
        return minTenancy;
    }

    public void setMinTenancy(int minTenancy) {
        this.minTenancy = minTenancy;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public boolean isPetFriendly() {
        return isPetFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        isPetFriendly = petFriendly;
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
        parcel.writeString(this.propertyStatus.name());
        parcel.writeString(this.propertyType.name());
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(metresSqr);
        parcel.writeString(description);
        parcel.writeInt(numBedrooms);
        parcel.writeInt(numBathrooms);
        parcel.writeByte((byte) (isParking ? 1 : 0));
        parcel.writeByte((byte) (isLift ? 1 : 0));
        parcel.writeString(rentPerMonth.toString());
        parcel.writeString(deposit.toString());
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
