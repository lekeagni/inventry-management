package com.example.order_service.model;

import lombok.Builder;

import java.beans.Transient;
import java.time.LocalDateTime;

@Builder
public record ErrorException(
        LocalDateTime localDateTime,
        int httpStatus,
        String message,
        @Transient
        String error
) {
}
