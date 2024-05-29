package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.converter.MemberResponseConverter;
import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import com.zeroskill.buytopia.dto.MemberResponseDto;
import com.zeroskill.buytopia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberResponseConverter memberResponseConverter;

    @PostMapping("/")
    public ResponseEntity<MemberResponseDto> registerMember(@RequestBody MemberRegistrationDto memberRegistrationDto) {
        MemberResponseDto memberResponseDto = memberService.registerMember(memberRegistrationDto);
        return memberResponseConverter.convertToResponseEntity(memberResponseDto);
    }
}
