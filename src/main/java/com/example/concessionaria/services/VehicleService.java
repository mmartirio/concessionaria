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

    //Método para obter todos os carros
    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    //Método para obter todas as motos
    public List<MotorcycleModel> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    //Método para obter um carro por ID
    public ResponseEntity<CarModel> getCarById(UUID id) {
        Optional<CarModel> car = carRepository.findById(id);
        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Método para obter uma moto por ID
    public ResponseEntity<MotorcycleModel> getMotorcycleById(UUID id) {
        Optional<MotorcycleModel> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isPresent()) {
            return ResponseEntity.ok(motorcycle.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Método para criar um carro a partir do DTO
    public CarModel createCar(CarRecordDto carRecordDto) {
        CarModel carModel = new CarModel();
        carModel.setManufacturer(carRecordDto.manufacturer());
        carModel.setModel(carRecordDto.model());
        carModel.setDate(carRecordDto.date());
        carModel.setPrice(carRecordDto.price());
        carModel.setFuel(carRecordDto.fuel());
        carModel.setDoors(carRecordDto.doors());
        carModel.setIdVehicle(UUID.randomUUID());

        return carRepository.save(carModel);
    }

    //Método para criar uma moto a partir do DTO
    public MotorcycleModel createMotorcycle(MotorcycleRecordDto motorcycleRecordDto) {
        MotorcycleModel motorcycleModel = new MotorcycleModel(
                UUID.randomUUID(),
                motorcycleRecordDto.manufacturer(),
                motorcycleRecordDto.model(),
                motorcycleRecordDto.date(),
                motorcycleRecordDto.price(),
                motorcycleRecordDto.cylinder(),
                motorcycleRecordDto.fuel()
        );

        return motorcycleRepository.save(motorcycleModel);
    }

    public ResponseEntity<CarModel> updateCar(UUID id, CarRecordDto carRecordDto) {
        Optional<CarModel> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            CarModel car = optionalCar.get();
            car.setManufacturer(carRecordDto.manufacturer());
            car.setModel(carRecordDto.model());
            car.setDate(carRecordDto.date());
            car.setPrice(carRecordDto.price());
            car.setFuel(carRecordDto.fuel());
            car.setDoors(carRecordDto.doors());

            CarModel updatedCar = carRepository.save(car);
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Método para atualizar uma moto
    public MotorcycleModel updateMotorcycle(UUID id, MotorcycleRecordDto motorcycleRecordDto) {
        Optional<MotorcycleModel> optionalMotorcycle = motorcycleRepository.findById(id);
        if (optionalMotorcycle.isPresent()) {
            MotorcycleModel motorcycle = optionalMotorcycle.get();
            motorcycle.setManufacturer(motorcycleRecordDto.manufacturer());
            motorcycle.setModel(motorcycleRecordDto.model());
            motorcycle.setDate(motorcycleRecordDto.date());
            motorcycle.setPrice(motorcycleRecordDto.price());
            motorcycle.setCylinder(motorcycleRecordDto.cylinder());

            return motorcycleRepository.save(motorcycle);
        } else {
            throw new IllegalArgumentException("Moto não encontrada");
        }
    }


    //Método para excluir um carro
    public ResponseEntity<Void> deleteCar(UUID id) {
        Optional<CarModel> car = carRepository.findById(id);
        if (car.isPresent()) {
            carRepository.delete(car.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Método para excluir uma moto
    public ResponseEntity<Void> deleteMotorcycle(UUID id) {
        Optional<MotorcycleModel> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isPresent()) {
            motorcycleRepository.delete(motorcycle.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
