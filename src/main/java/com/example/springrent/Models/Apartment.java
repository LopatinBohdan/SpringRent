package com.example.springrent.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apartment_id")
    private Long id;
    private String title="";
    private int roomsAmount;
    private String address="";
    private float price;
    @ManyToOne
    @JoinColumn(name = "landlord_id", nullable = false, insertable = false, updatable = false)
    private Landlord landlord;
    @OneToMany(mappedBy = "apartment")
    private List<Rent> rents;

    public Apartment(){}

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public Apartment(String title, int roomsAmount, String address, float price, Landlord landlord) {
        this.title = title;
        this.roomsAmount = roomsAmount;
        this.address = address;
        this.price = price;
        this.landlord = landlord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(int roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }
}
