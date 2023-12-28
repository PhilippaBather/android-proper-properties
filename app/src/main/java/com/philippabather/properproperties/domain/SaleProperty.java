package com.philippabather.properproperties.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

/**
 * Sale Property - la clase define un inmueble para vender; implementa Parcelable.
 *
 * @author Philippa Bather
 */
public class SaleProperty implements Parcelable {

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
    private BigDecimal price;
    private boolean isLeasehold;
    private boolean isFavourite;

    public SaleProperty(PropertyStatus propertyStatus, PropertyType propertyType,
                        double latitude, double longitude, int metresSqr, String description,
                        int numBedrooms, int numBathrooms, boolean isParking, boolean isLift,
                        BigDecimal price, boolean isLeasehold) {
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
        this.price = price;
        this.isLeasehold = isLeasehold;
        this.isFavourite = false;
    }

    public SaleProperty(long id, PropertyStatus propertyStatus, PropertyType propertyType,
                        double latitude, double longitude, int metresSqr, String description,
                        int numBedrooms, int numBathrooms, boolean isParking, boolean isLift,
                        BigDecimal price, boolean isLeasehold) {
        this(propertyStatus, propertyType, latitude, longitude, metresSqr, description, numBedrooms,
                numBathrooms, isParking, isLift, price, isLeasehold);
        this.id = id;
    }

    protected SaleProperty(Parcel in) {
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
        price = new BigDecimal(in.readString());
        isLeasehold = in.readByte() != 0;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isLeasehold() {
        return isLeasehold;
    }

    public void setLeasehold(boolean leasehold) {
        isLeasehold = leasehold;
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
        parcel.writeString(price.toString());
        parcel.writeByte((byte) (isLeasehold ? 1 : 0));
        parcel.writeByte((byte) (isFavourite ? 1 : 0));
    }

    @Override
    public String toString() {
        return "SaleProperty{" +
                "id=" + id +
                ", propertyStatus=" + propertyStatus +
                ", propertyType=" + propertyType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", metresSqr=" + metresSqr +
                ", description='" + description + '\'' +
                ", numBedrooms=" + numBedrooms +
                ", numBathrooms=" + numBathrooms +
                ", isParking=" + isParking +
                ", isLift=" + isLift +
                ", price=" + price +
                ", isLeasehold=" + isLeasehold +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
