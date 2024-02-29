package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StudentRequest(@NotBlank String name, @NotNull int age, @NotBlank String nip, @NotBlank UUID roomClassId) {
}
