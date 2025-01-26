package com.example.concessionaria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_MOTORCYCLES")
public class MotorcycleModel {

    @Id
    private UUID idVehicle;

    private String manufacturer;
    private String model;
    private String year;
    private BigDecimal price;
    private String fuel;
    private BigDecimal cylinder;

    public MotorcycleModel() {}

    public MotorcycleModel(UUID idVehicle, String manufacturer, String model, String year, BigDecimal price, String fuel, BigDecimal cylinder) {
        this.idVehicle = idVehicle;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.price = price;
        this.fuel = fuel;
        this.cylinder = cylinder;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public BigDecimal getCylinder() {
        return cylinder;
    }

    public void setCylinder(BigDecimal cylinder) {
        this.cylinder = cylinder;
    }
}
