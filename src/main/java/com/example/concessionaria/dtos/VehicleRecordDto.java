package com.example.concessionaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleRecordDto(@NotBlank String manufacturer, @NotBlank String model, @NotNull
LocalDate date, @NotBlank String color, @NotNull BigDecimal km, @NotBlank String fuel) {
}