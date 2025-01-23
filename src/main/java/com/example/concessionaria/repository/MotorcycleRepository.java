package com.example.concessionaria.repository;

import com.example.concessionaria.model.MotorcycleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotorcycleRepository extends JpaRepository<MotorcycleModel, UUID> {
}

