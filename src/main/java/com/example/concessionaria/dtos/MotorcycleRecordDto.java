package com.example.concessionaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MotorcycleRecordDto(
        @NotBlank(message = "O fabricante não pode ser vazio.")
        String manufacturer,

        @NotBlank(message = "O modelo não pode ser vazio.")
        String model,

        @NotBlank(message = "O ano não pode ser vazio.")
        String year,

        @NotNull(message = "O preço não pode ser nulo.")
        @Positive(message = "O preço deve ser um valor positivo.")
        BigDecimal price,

        @NotNull(message = "A cilindrada não pode ser nula.")
        @Positive(message = "A cilindrada deve ser um valor positivo.")
        BigDecimal cylinder,

        @NotBlank(message = "O tipo de combustível não pode ser vazio.")
        String fuel
) {}
