package com.example.concessionaria.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class VehicleModel extends RepresentationModel<VehicleModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVehicle;

    private String manufacturer;
    private String model;
    private String date;
    private BigDecimal price;
    private String vehicleType;

    public VehicleModel() {
    }

    public VehicleModel(UUID idVehicle, String manufacturer, String model, String date, BigDecimal price, String vehicleType) {
        this.idVehicle = idVehicle;
        this.manufacturer = manufacturer;
        this.model = model;
        this.date = date;
        this.price = price;
        this.vehicleType = vehicleType;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
