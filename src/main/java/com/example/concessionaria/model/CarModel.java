package com.example.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_CARS")
public class CarModel extends VehicleModel {
    private int doors;
    private double engine;
    private String transmission;

    public CarModel() {
    }

    public CarModel(UUID idVehicle, String manufacturer, String model, LocalDate date, String color, BigDecimal km, String fuel, int doors, double engine, String transmission) {
        super(idVehicle, manufacturer, model, date, color, km, fuel);
        this.doors = doors;
        this.engine = engine;
        this.transmission = transmission;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}







