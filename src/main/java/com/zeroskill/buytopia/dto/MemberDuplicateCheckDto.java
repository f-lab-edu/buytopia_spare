package com.zeroskill.buytopia.dto;

import jakarta.validation.constraints.NotNull;

public record MemberDuplicateCheckDto(
        @NotNull
        String memberId,
        @NotNull
        String email
) {
}
