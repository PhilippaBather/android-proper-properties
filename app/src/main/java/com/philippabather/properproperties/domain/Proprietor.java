package com.philippabather.properproperties.domain;

import java.util.List;

/**
 * Proprietor - clase define un propietario; implementa Parcelable.
 *
 * @author Philippa Bather
 */
public class Proprietor {
    private String username;
    private String name;
    private String surname;
    private String email;
    private long id;
    private int numProperties;
    private boolean isAgency;
    private List<RentalProperty> rentalPropertyList;
    private List<SaleProperty> salePropertyList;

    public Proprietor(String username, String name, String surname, String email,
                      long id, int numProperties, boolean isAgency,
                      List<RentalProperty> rentalPropertyList, List<SaleProperty> salePropertyList) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id = id;
        this.numProperties = numProperties;
        this.isAgency = isAgency;
        this.rentalPropertyList = rentalPropertyList;
        this.salePropertyList = salePropertyList;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public int getNumProperties() {
        return numProperties;
    }

    public boolean isAgency() {
        return isAgency;
    }

    public List<RentalProperty> getRentalPropertyList() {
        return rentalPropertyList;
    }

    public List<SaleProperty> getSalePropertyList() {
        return salePropertyList;
    }

    @Override
    public String toString() {
        return "Proprietor{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", numProperties=" + numProperties +
                ", isAgency=" + isAgency +
                ", rentalPropertyList=" + rentalPropertyList +
                ", salePropertyList=" + salePropertyList +
                '}';
    }
}
