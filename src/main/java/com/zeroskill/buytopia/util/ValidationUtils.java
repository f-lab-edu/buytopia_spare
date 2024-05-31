package com.zeroskill.buytopia.util;

import com.zeroskill.buytopia.dto.MemberDuplicateResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationUtils {
    public static ResponseEntity<MemberDuplicateResponseDto> handleFieldErrors(String errorMessage) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MemberDuplicateResponseDto(errorMessage, HttpStatus.BAD_REQUEST));
    }
}
