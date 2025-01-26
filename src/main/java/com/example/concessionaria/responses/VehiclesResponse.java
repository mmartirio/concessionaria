package com.example.concessionaria.responses;

import com.example.concessionaria.model.CarModel;
import com.example.concessionaria.model.MotorcycleModel;
import java.util.List;

public class VehiclesResponse {
    private List<CarModel> cars;
    private List<MotorcycleModel> motorcycles;

    public VehiclesResponse(List<CarModel> cars, List<MotorcycleModel> motorcycles) {
        this.cars = cars;
        this.motorcycles = motorcycles;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    public List<MotorcycleModel> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<MotorcycleModel> motorcycles) {
        this.motorcycles = motorcycles;
    }
}
