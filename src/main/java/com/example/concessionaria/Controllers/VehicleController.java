package com.example.concessionaria.Controllers;

import com.example.concessionaria.repository.VehicleRepository;
import com.example.concessionaria.dtos.VehicleRecordDto;
import com.example.concessionaria.model.VehicleModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getAllVehicles() {
        List<VehicleModel> vehicleList = vehicleRepository.findAll();
        if (!vehicleList.isEmpty()) {
            for (VehicleModel vehicle : vehicleList) {
                UUID id = vehicle.getIdVehicle();
                vehicle.add(linkTo(methodOn(VehicleController.class).getOneVehicle(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVehicle(@PathVariable(value = "id") UUID id) {
        Optional<VehicleModel> vehicleO = vehicleRepository.findById(id);
        if (vehicleO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        VehicleModel vehicle = vehicleO.get();
        vehicle.add(linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("all-vehicles"));
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid VehicleRecordDto vehicleRecordDto) {
        Optional<VehicleModel> vehicleO = vehicleRepository.findById(id);
        if (vehicleO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        VehicleModel vehicleModel = vehicleO.get();
        BeanUtils.copyProperties(vehicleRecordDto, vehicleModel, "idVehicle");
        return ResponseEntity.status(HttpStatus.OK).body(vehicleRepository.save(vehicleModel));
    }

    @PostMapping
    public ResponseEntity<VehicleModel> createVehicle(@RequestBody @Valid VehicleRecordDto vehicleRecordDto) {
        VehicleModel vehicleModel = new VehicleModel();
        BeanUtils.copyProperties(vehicleRecordDto, vehicleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleRepository.save(vehicleModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable(value = "id") UUID id) {
        Optional<VehicleModel> vehicleO = vehicleRepository.findById(id);
        if (vehicleO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        vehicleRepository.delete(vehicleO.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Vehicle deleted successfully");
    }
}
