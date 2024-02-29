package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;

public record RoomClassRequest(@NotBlank String code) {
}
