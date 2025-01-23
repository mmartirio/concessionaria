package com.example.concessionaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CarRecordDto(
        @NotBlank String manufacturer,
        @NotBlank String model,
        @NotBlank String date,
        @NotNull BigDecimal price,
        @NotBlank String vehicleType,
        @NotNull Integer doors,
        @NotBlank String fuel
) {

}