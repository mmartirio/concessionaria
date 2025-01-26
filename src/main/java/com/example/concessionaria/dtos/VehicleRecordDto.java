package com.example.concessionaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record VehicleRecordDto(
        String manufacturer,
        String model,
        String year,
        BigDecimal price,
        String vehicleType,
        Integer doors,
        String fuel,
        Integer cylinder
) {}

