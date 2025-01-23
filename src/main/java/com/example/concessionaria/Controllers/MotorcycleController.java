package com.example.concessionaria.controllers;

import com.example.concessionaria.repository.MotorcycleRepository;
import com.example.concessionaria.model.MotorcycleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    MotorcycleRepository motorcycleRepository;

    @PostMapping
    public ResponseEntity<MotorcycleModel> saveMotorcycle(@RequestBody MotorcycleModel motorcycle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motorcycleRepository.save(motorcycle));
    }

    @GetMapping
    public ResponseEntity<List<MotorcycleModel>> getAllMotorcycles() {
        return ResponseEntity.status(HttpStatus.OK).body(motorcycleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMotorcycleById(@PathVariable UUID id) {
        Optional<MotorcycleModel> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorcycle not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(motorcycle.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMotorcycle(@PathVariable UUID id) {
        Optional<MotorcycleModel> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorcycle not found");
        }
        motorcycleRepository.delete(motorcycle.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
