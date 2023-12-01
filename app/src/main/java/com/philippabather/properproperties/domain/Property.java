package com.philippabather.properproperties.domain;

import java.time.LocalDate;

public class Property {

    private long id;
    private PropertyStatus propertyStatus;
    private PropertyType propertyType;
    private String description;
    private int metresSqr;
    private double latitude;
    private double longitude;
    private LocalDate availableFrom;
    private int numBedrooms;
    private int numBathrooms;
    private boolean isParking;
    private boolean isLift; // hay ascensor

    public Property(long id, PropertyStatus propertyStatus, PropertyType propertyType,
                    double latitude, double longitude, LocalDate availableFrom, int numBedrooms,
                    int numBathrooms, boolean isParking) {
        this.id = id;
        this.propertyStatus = propertyStatus;
        this.propertyType = propertyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableFrom = availableFrom;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.isParking = isParking;
    }

    public long getId() {
        return id;
    }
}
