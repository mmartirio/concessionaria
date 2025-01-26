package com.example.concessionaria.services;

import com.example.concessionaria.dtos.CarRecordDto;
import com.example.concessionaria.dtos.MotorcycleRecordDto;
import com.example.concessionaria.model.CarModel;
import com.example.concessionaria.model.MotorcycleModel;
import com.example.concessionaria.repository.CarRepository;
import com.example.concessionaria.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    private final CarRepository carRepository;
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public VehicleService(CarRepository carRepository, MotorcycleRepository motorcycleRepository) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    // Método para criar um carro
    public CarModel createCar(CarRecordDto carRecordDto) {
        CarModel carModel = new CarModel();
        carModel.setManufacturer(carRecordDto.manufacturer());
        carModel.setModel(carRecordDto.model());
        carModel.setYear(carRecordDto.year());  // Alterado para year
        carModel.setPrice(carRecordDto.price());
        carModel.setFuel(carRecordDto.fuel());
        carModel.setDoors(carRecordDto.doors());
        carModel.setIdVehicle(UUID.randomUUID());

        return carRepository.save(carModel);
    }

    // Método para criar uma moto
    public MotorcycleModel createMotorcycle(MotorcycleRecordDto motorcycleRecordDto) {
        MotorcycleModel motorcycleModel = new MotorcycleModel();
        motorcycleModel.setManufacturer(motorcycleRecordDto.manufacturer());
        motorcycleModel.setModel(motorcycleRecordDto.model());
        motorcycleModel.setYear(motorcycleRecordDto.year());  // Alterado para year
        motorcycleModel.setPrice(motorcycleRecordDto.price());
        motorcycleModel.setCylinder(motorcycleRecordDto.cylinder());
        motorcycleModel.setFuel(motorcycleRecordDto.fuel());
        motorcycleModel.setIdVehicle(UUID.randomUUID());

        return motorcycleRepository.save(motorcycleModel);
    }

    // Método para atualizar um carro
    public ResponseEntity<CarModel> updateCar(UUID id, CarRecordDto carRecordDto) {
        Optional<CarModel> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            CarModel car = optionalCar.get();
            car.setManufacturer(carRecordDto.manufacturer());
            car.setModel(carRecordDto.model());
            car.setYear(carRecordDto.year());  // Alterado para year
            car.setPrice(carRecordDto.price());
            car.setFuel(carRecordDto.fuel());
            car.setDoors(carRecordDto.doors());

            CarModel updatedCar = carRepository.save(car);
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Método para atualizar uma moto
    public MotorcycleModel updateMotorcycle(UUID id, MotorcycleRecordDto motorcycleRecordDto) {
        Optional<MotorcycleModel> optionalMotorcycle = motorcycleRepository.findById(id);
        if (optionalMotorcycle.isPresent()) {
            MotorcycleModel motorcycle = optionalMotorcycle.get();
            motorcycle.setManufacturer(motorcycleRecordDto.manufacturer());
            motorcycle.setModel(motorcycleRecordDto.model());
            motorcycle.setYear(motorcycleRecordDto.year());  // Alterado para year
            motorcycle.setPrice(motorcycleRecordDto.price());
            motorcycle.setCylinder(motorcycleRecordDto.cylinder());

            return motorcycleRepository.save(motorcycle);
        } else {
            throw new IllegalArgumentException("Moto não encontrada");
        }
    }

    // Método para obter todos os carros
    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    // Método para obter todas as motos
    public List<MotorcycleModel> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    // Método para obter um carro por ID
    public CarModel getCarById(UUID id) {
        return carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
    }

    // Método para obter uma moto por ID
    public MotorcycleModel getMotorcycleById(UUID id) {
        return motorcycleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Moto não encontrada"));
    }


    // Método para deletar um carro
    public void deleteCar(UUID id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Carro não encontrado");
        }
    }

    // Método para deletar uma moto
    public void deleteMotorcycle(UUID id) {
        if (motorcycleRepository.existsById(id)) {
            motorcycleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Moto não encontrada");
        }
    }
}
