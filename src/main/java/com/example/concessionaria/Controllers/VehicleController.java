package com.example.concessionaria.Controllers;

import com.example.concessionaria.dtos.CarRecordDto;
import com.example.concessionaria.dtos.MotorcycleRecordDto;
import com.example.concessionaria.model.CarModel;
import com.example.concessionaria.model.MotorcycleModel;
import com.example.concessionaria.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    // Construtor que injeta o serviço VehicleService
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Criação de um Carro
    @PostMapping("/createCar")
    public ResponseEntity<CarModel> createCar(@RequestBody CarRecordDto carRecordDto) {
        CarModel createdCar = vehicleService.createCar(carRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    // Criação de uma Moto
    @PostMapping("/createMotorcycle")
    public ResponseEntity<MotorcycleModel> createMotorcycle(@RequestBody MotorcycleRecordDto motorcycleRecordDto) {
        MotorcycleModel createdMotorcycle = vehicleService.createMotorcycle(motorcycleRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMotorcycle);
    }

    // Atualização de um Carro
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<CarModel> updateCar(@PathVariable UUID id, @RequestBody CarRecordDto carRecordDto) {
        return vehicleService.updateCar(id, carRecordDto);  // Apenas repassa a chamada para o serviço
    }

    // Atualização de uma Moto
    @PutMapping("/updateMotorcycle/{id}")
    public MotorcycleModel updateMotorcycle(@PathVariable UUID id, @RequestBody MotorcycleRecordDto motorcycleRecordDto) {
        return vehicleService.updateMotorcycle(id, motorcycleRecordDto);  // Apenas chama o serviço
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
