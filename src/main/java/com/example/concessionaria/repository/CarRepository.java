package com.example.concessionaria.repository;

import com.example.concessionaria.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID> {
}
