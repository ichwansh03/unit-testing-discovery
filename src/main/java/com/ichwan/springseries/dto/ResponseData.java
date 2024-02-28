package com.ichwan.springseries.dto;

public record ResponseData<T>(String status, T data) {
}
