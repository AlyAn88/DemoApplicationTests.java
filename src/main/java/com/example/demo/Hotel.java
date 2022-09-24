package com.example.demo;

import java.io.Serializable;

public class Hotel implements Serializable {
    public int DE;
    private int ID;
    private String hotelName;
    private String locatie;
    private int camere;

    public Hotel() {
        super();
    }
    public void setDE(int DE) {
        this.DE = DE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setCamere(int camere) {
        this.camere = camere;
    }

    public int getID() {
        return ID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocatie() {
        return locatie;
    }

    public int getCamere() {
        return camere;
    }
}

