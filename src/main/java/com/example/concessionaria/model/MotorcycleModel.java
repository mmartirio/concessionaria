package com.example.concessionaria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_MOTORCYCLES")
public class MotorcycleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVehicle;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer cylinder;

    @Column(nullable = false)
    private String fuel;

    public MotorcycleModel() {}

    public MotorcycleModel(UUID idVehicle, String manufacturer, String model, String date, BigDecimal price, Integer cylinder, String fuel) {
        this.idVehicle = idVehicle;
        this.manufacturer = manufacturer;
        this.model = model;
        this.date = date;
        this.price = price;
        this.cylinder = cylinder;
        this.fuel = fuel;
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

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
