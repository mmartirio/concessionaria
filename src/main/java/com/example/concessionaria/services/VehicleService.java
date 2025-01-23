package com.example.concessionaria.services;

import com.example.concessionaria.model.CarModel;
import com.example.concessionaria.model.MotorcycleModel;
import com.example.concessionaria.repository.CarRepository;
import com.example.concessionaria.repository.MotorcycleRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class VehicleService {

    private final CarRepository carRepository;
    private final MotorcycleRepository motorcycleRepository;

    public VehicleService(CarRepository carRepository, MotorcycleRepository motorcycleRepository) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    public List<MotorcycleModel> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    public EntityModel<CarModel> getCarById(UUID id) {
        Optional<CarModel> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new RuntimeException("Carro não encontrado!");
        }

        return EntityModel.of(
                car.get(),
                linkTo(methodOn(VehicleService.class).getCarById(id)).withSelfRel(),
                linkTo(methodOn(VehicleService.class).getAllCars()).withRel("all-cars")
        );
    }

    public EntityModel<MotorcycleModel> getMotorcycleById(UUID id) {
        Optional<MotorcycleModel> moto = motorcycleRepository.findById(id);
        if (moto.isEmpty()) {
            throw new RuntimeException("Moto não encontrada!");
        }

        return EntityModel.of(
                moto.get(),
                linkTo(methodOn(VehicleService.class).getMotorcycleById(id)).withSelfRel(),
                linkTo(methodOn(VehicleService.class).getAllMotorcycles()).withRel("all-motorcycles")
        );
    }

    public CarModel createCar(CarModel carModel) {
        carModel.setIdVehicle(UUID.randomUUID());
        return carRepository.save(carModel);
    }

    public MotorcycleModel createMotorcycle(MotorcycleModel motorcycleModel) {
        motorcycleModel.setIdVehicle(UUID.randomUUID());
        return motorcycleRepository.save(motorcycleModel);
    }

    public CarModel updateCar(UUID id, CarModel carModel) {
        return carRepository.findById(id)
                .map(existingCar -> {
                    existingCar.setManufacturer(carModel.getManufacturer());
                    existingCar.setModel(carModel.getModel());
                    existingCar.setDate(carModel.getDate());
                    existingCar.setColor(carModel.getColor());
                    existingCar.setKm(carModel.getKm());
                    existingCar.setFuel(carModel.getFuel());
                    existingCar.setDoors(carModel.getDoors());
                    existingCar.setEngine(carModel.getEngine());
                    existingCar.setTransmission(carModel.getTransmission());
                    return carRepository.save(existingCar);
                })
                .orElseThrow(() -> new RuntimeException("Carro não encontrado!"));
    }

    public MotorcycleModel updateMotorcycle(UUID id, MotorcycleModel motorcycleModel) {
        return motorcycleRepository.findById(id)
                .map(existingMoto -> {
                    existingMoto.setManufacturer(motorcycleModel.getManufacturer());
                    existingMoto.setModel(motorcycleModel.getModel());
                    existingMoto.setDate(motorcycleModel.getDate());
                    existingMoto.setColor(motorcycleModel.getColor());
                    existingMoto.setKm(motorcycleModel.getKm());
                    existingMoto.setFuel(motorcycleModel.getFuel());
                    existingMoto.setCylinder(motorcycleModel.getCylinder());
                    return motorcycleRepository.save(existingMoto);
                })
                .orElseThrow(() -> new RuntimeException("Moto não encontrada!"));
    }
    public void deleteCar(UUID id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Carro não encontrado!");
        }
    }

    public void deleteMotorcycle(UUID id) {
        if (motorcycleRepository.existsById(id)) {
            motorcycleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Moto não encontrada!");
        }
    }
}
