package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomClassRequest(@NotBlank String code, @NotNull int totalTable) {
}
