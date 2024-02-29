package com.ichwan.springseries.dto;

import jakarta.validation.constraints.NotBlank;

public record TeacherRequest(@NotBlank String name, @NotBlank String subject, @NotBlank String nik) {
}
