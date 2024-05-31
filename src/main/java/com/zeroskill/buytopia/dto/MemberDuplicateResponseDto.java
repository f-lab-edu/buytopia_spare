package com.zeroskill.buytopia.dto;

import org.springframework.http.HttpStatus;

public record MemberDuplicateResponseDto(
        String message,
        HttpStatus status
) {
}
