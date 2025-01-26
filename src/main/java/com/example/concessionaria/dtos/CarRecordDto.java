package com.example.concessionaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CarRecordDto(
        @NotBlank(message = "O fabricante não pode ser vazio.")
        String manufacturer,

        @NotBlank(message = "O modelo não pode ser vazio.")
        String model,

        @NotBlank(message = "O ano não pode ser vazio.")
        String year,

        @NotNull(message = "O preço não pode ser nulo.")
        @Positive(message = "O preço deve ser um valor positivo.")
        BigDecimal price,

        @NotBlank(message = "O tipo do veículo não pode ser vazio.")
        String vehicleType,

        @NotNull(message = "O número de portas não pode ser nulo.")
        @Positive(message = "O número de portas deve ser um valor positivo.")
        Integer doors,

        @NotBlank(message = "O tipo de combustível não pode ser vazio.")
        String fuel
) {}
