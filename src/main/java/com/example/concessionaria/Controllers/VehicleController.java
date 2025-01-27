package com.example.concessionaria.Controllers;

import com.example.concessionaria.dtos.CarRecordDto;
import com.example.concessionaria.dtos.MotorcycleRecordDto;
import com.example.concessionaria.model.CarModel;
import com.example.concessionaria.model.MotorcycleModel;
import com.example.concessionaria.responses.VehiclesResponse;
import com.example.concessionaria.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:63342")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Listar todos os veículos (Carros e Motos)
    @GetMapping
    public ResponseEntity<Object> getAllVehicles() {
        try {
            List<CarModel> cars = vehicleService.getAllCars();
            List<MotorcycleModel> motorcycles = vehicleService.getAllMotorcycles();

            VehiclesResponse response = new VehiclesResponse(cars, motorcycles);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar veículos: " + e.getMessage());
        }
    }

    // Listar todos os Carros
    @GetMapping("/cars")
    public ResponseEntity<Object> getAllCars() {
        try {
            List<CarModel> cars = vehicleService.getAllCars();
            return ResponseEntity.ok().body(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar carros: " + e.getMessage());
        }
    }

    // Listar todas as Motos
    @GetMapping("/motorcycles")
    public ResponseEntity<Object> getAllMotorcycles() {
        try {
            List<MotorcycleModel> motorcycles = vehicleService.getAllMotorcycles();
            return ResponseEntity.ok().body(motorcycles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar motos: " + e.getMessage());
        }
    }

    // Buscar um Carro por ID
    @GetMapping("/car/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable UUID id) {
        try {
            CarModel car = vehicleService.getCarById(id);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Buscar uma Moto por ID
    @GetMapping("/motorcycle/{id}")
    public ResponseEntity<MotorcycleModel> getMotorcycleById(@PathVariable UUID id) {
        try {
            MotorcycleModel motorcycle = vehicleService.getMotorcycleById(id);
            return ResponseEntity.ok(motorcycle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Criação de um Carro
    @PostMapping("/createCar")
    public ResponseEntity<CarModel> createCar(@Valid @RequestBody CarRecordDto carRecordDto) {
        CarModel createdCar = vehicleService.createCar(carRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    // Criação de uma Moto
    @PostMapping("/createMotorcycle")
    public ResponseEntity<MotorcycleModel> createMotorcycle(@Valid @RequestBody MotorcycleRecordDto motorcycleRecordDto) {
        MotorcycleModel createdMotorcycle = vehicleService.createMotorcycle(motorcycleRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMotorcycle);
    }

    // Atualização de um Carro
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<CarModel> updateCar(@PathVariable UUID id, @RequestBody CarRecordDto carRecordDto) {
        return vehicleService.updateCar(id, carRecordDto);
    }

    // Atualização de uma Moto
    @PutMapping("/updateMotorcycle/{id}")
    public MotorcycleModel updateMotorcycle(@PathVariable UUID id, @RequestBody MotorcycleRecordDto motorcycleRecordDto) {
        return vehicleService.updateMotorcycle(id, motorcycleRecordDto);
    }

    // Deletar Carro
    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        vehicleService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Deletar Moto
    @DeleteMapping("/deleteMotorcycle/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable UUID id) {
        vehicleService.deleteMotorcycle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
