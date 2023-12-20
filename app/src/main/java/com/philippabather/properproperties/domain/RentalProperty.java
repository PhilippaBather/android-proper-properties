package com.philippabather.properproperties.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalProperty {

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
