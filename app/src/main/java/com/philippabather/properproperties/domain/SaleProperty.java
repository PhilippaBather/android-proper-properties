package com.philippabather.properproperties.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleProperty {

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

    @Override
    public String toString() {
        return "SaleProperty{" +
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
                ", price=" + price +
                ", isLeasehold=" + isLeasehold +
                ", constructionDate='" + constructionDate + '\'' +
                ", constructionDateLD=" + constructionDateLD +
                '}';
    }
}
