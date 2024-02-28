package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;

public record ClazzDTO(@NotBlank String code) {
}
