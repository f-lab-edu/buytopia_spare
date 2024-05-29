package com.zeroskill.buytopia.converter;

import com.zeroskill.buytopia.dto.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberResponseConverter {

    public ResponseEntity<MemberResponseDto> convertToResponseEntity(MemberResponseDto userResponseDto) {
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
