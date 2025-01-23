package com.example.concessionaria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_CARS")
public class CarModel {

    @Id
    private UUID idVehicle;
    private String manufacturer;
    private String model;
    private String date;
    private BigDecimal price;
    private String fuel;
    private Integer doors;

    public CarModel() {
    }

    public CarModel(UUID idVehicle, String manufacturer, String model, String date, BigDecimal price, String fuel, Integer doors) {
        this.idVehicle = idVehicle;
        this.manufacturer = manufacturer;
        this.model = model;
        this.date = date;
        this.price = price;
        this.fuel = fuel;
        this.doors = doors;
    }

    public UUID getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(UUID idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }
}
