package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentDTO(@NotBlank String name, @NotNull int age, @NotBlank String nip) {
}
