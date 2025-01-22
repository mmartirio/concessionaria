package com.example.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_MOTORCYCLES")
public class MotorcycleModel extends VehicleModel {
    private int cylinder;

    public MotorcycleModel() {
    }

    public MotorcycleModel(UUID idVehicle, String manufacturer, String model, LocalDate date, String color, BigDecimal km, String fuel, int cylinder) {
        super(idVehicle, manufacturer, model, date, color, km, fuel);
        this.cylinder = cylinder;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }
}