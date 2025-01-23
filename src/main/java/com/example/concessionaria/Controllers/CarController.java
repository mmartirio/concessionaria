package com.example.concessionaria.controllers;

import com.example.concessionaria.repository.CarRepository;
import com.example.concessionaria.dtos.CarRecordDto;
import com.example.concessionaria.model.CarModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars() {
        List<CarModel> carList = carRepository.findAll();
        if (!carList.isEmpty()) {
            for (CarModel car : carList) {
                UUID id = car.getIdVehicle();
                car.add(linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarById(@PathVariable(value = "id") UUID id) {
        Optional<CarModel> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        CarModel car = carOptional.get();
        car.add(linkTo(methodOn(CarController.class).getAllCars()).withRel("all-cars"));
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @PostMapping
    public ResponseEntity<CarModel> createCar(@RequestBody @Valid CarRecordDto carRecordDto) {
        CarModel carModel = new CarModel();
        carModel.setManufacturer(carRecordDto.manufacturer());
        carModel.setModel(carRecordDto.model());
        carModel.setDate(carRecordDto.date());
        carModel.setColor(carRecordDto.color());
        carModel.setKm(carRecordDto.km());
        carModel.setFuel(carRecordDto.fuel());
        carModel.setDoors(carRecordDto.doors());
        carModel.setEngine(carRecordDto.engine());
        carModel.setTransmission(carRecordDto.transmission());
        carModel.setSeats(carRecordDto.seats());

        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(carModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarRecordDto carRecordDto) {
        Optional<CarModel> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        CarModel carModel = carOptional.get();
        carModel.setManufacturer(carRecordDto.manufacturer());
        carModel.setModel(carRecordDto.model());
        carModel.setDate(carRecordDto.date());
        carModel.setColor(carRecordDto.color());
        carModel.setKm(carRecordDto.km());
        carModel.setFuel(carRecordDto.fuel());
        carModel.setDoors(carRecordDto.doors());
        carModel.setEngine(carRecordDto.engine());
        carModel.setTransmission(carRecordDto.transmission());
        carModel.setSeats(carRecordDto.seats());

        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(carModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id) {
        Optional<CarModel> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        carRepository.delete(carOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
