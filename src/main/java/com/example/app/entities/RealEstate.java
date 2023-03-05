package com.example.app.entities;

import com.example.app.models.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "realestate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String owner;
    private Double monthlyRate;
    private Type type;
    private Boolean parking;

    @Column(name = "realestatedate")
    private LocalDate realEstateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(Double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = false;
    }

    public LocalDate getRealEstateDate() {
        return realEstateDate;
    }

    public void setRealEstateDate(LocalDate realEstateDate) {
        this.realEstateDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealEstate that = (RealEstate) o;
        return Objects.equals(id, that.id) && Objects.equals(owner, that.owner) && Objects.equals(monthlyRate, that.monthlyRate) && type == that.type && Objects.equals(parking, that.parking) && Objects.equals(realEstateDate, that.realEstateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, monthlyRate, type, parking, realEstateDate);
    }


}
