package com.example.category_service.model;

import lombok.Builder;

import java.beans.Transient;
import java.time.LocalDateTime;

@Builder
public record ErrorException(
        LocalDateTime localDateTime,
        Integer httpStatus,
        String message,
        @Transient
        String error

)
{ }
