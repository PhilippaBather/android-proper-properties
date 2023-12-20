package com.philippabather.properproperties.domain;

import java.time.LocalDate;

public class Property {

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

    private boolean isLift;

    private boolean isParking;

    public Property(long id, PropertyStatus propertyStatus, PropertyType propertyType, double latitude,
                    double longitude, int metresSqr, String description, String availableFrom,
                    int numBedrooms, int numBathrooms, boolean isLift, boolean isParking) {
        this.id = id;
        this.propertyStatus = propertyStatus;
        this.propertyType = propertyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metresSqr = metresSqr;
        this.description = description;
        this.availableFromLD = LocalDate.parse(availableFrom);
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.isLift = isLift;
        this.isParking = isParking;
    }

    public long getId() {
        return id;
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
        return "Property{" +
                "id=" + id +
                ", propertyStatus=" + propertyStatus +
                ", propertyType=" + propertyType +
                ", description='" + description + '\'' +
                ", metresSqr=" + metresSqr +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", numBedrooms=" + numBedrooms +
                ", numBathrooms=" + numBathrooms +
                ", isParking=" + isParking +
                ", isLift=" + isLift +
                '}';
    }
}
