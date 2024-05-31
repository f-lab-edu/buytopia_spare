package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.converter.MemberResponseConverter;
import com.zeroskill.buytopia.dto.*;
import com.zeroskill.buytopia.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zeroskill.buytopia.util.ValidationUtils.handleFieldErrors;

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

    @PostMapping("/check-memberid-duplicate")
    public ResponseEntity<MemberDuplicateResponseDto> checkMemberId(@Valid @RequestBody MemberDuplicateCheckDto memberDuplicateCheckDto, BindingResult result) {
        if (result.hasFieldErrors("memberId")) {
            String errorMessage = "MemberId is required";
            return handleFieldErrors(errorMessage);
        }

        String memberId = memberDuplicateCheckDto.memberId();

        boolean isDuplicate = memberService.isMemberIdDuplicate(memberId);

        if (isDuplicate) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MemberDuplicateResponseDto("MemberId is already taken", HttpStatus.BAD_REQUEST));
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberDuplicateResponseDto("MemberId is available", HttpStatus.OK));
    }

    @PostMapping("/check-email")
    public ResponseEntity<MemberDuplicateResponseDto> checkEmail(@Valid @RequestBody MemberDuplicateCheckDto memberDuplicateCheckDto, BindingResult result) {
        if (result.hasFieldErrors("email")) {
            String errorMessage = "Email is required";
            return handleFieldErrors(errorMessage);
        }

        String email = memberDuplicateCheckDto.email();

        boolean isDuplicate = memberService.isEmailDuplicate(email);

        if (isDuplicate) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MemberDuplicateResponseDto("Email is already taken", HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberDuplicateResponseDto("Eamil is available", HttpStatus.OK));
    }

    @PostMapping("/check-availability")
    public ResponseEntity<MemberDuplicateResponseDto> checkMemberAvailability(@Valid @RequestBody MemberDuplicateCheckDto memberDuplicateCheckDto, BindingResult result) {
        if (result.hasFieldErrors("memberId") || result.hasFieldErrors("email")) {
            String errorMessage = "MemberId and email are required";
            return handleFieldErrors(errorMessage);
        }

        String memberId = memberDuplicateCheckDto.memberId();
        String email = memberDuplicateCheckDto.email();

        boolean isDuplicate = memberService.isMemberIdOrEmailDuplicate(memberId, email);

        if (isDuplicate) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MemberDuplicateResponseDto("MemberId or email is already taken", HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberDuplicateResponseDto("MemberId is already taken", HttpStatus.OK));
    }

    @PostMapping("/password-strength")
    public ResponseEntity<PasswordStrengthCheckResponseDto> checkMemberAvailability(@Valid @RequestBody PasswordStrengthCheckDto passwordStrengthCheckDto, BindingResult result) {
//        질문
//        if (result.hasFieldErrors("password") || result.hasFieldErrors("password")) {
//              String errorMessage = "Password is required";
//              return handleFieldErrors(errorMessage);
//        }
        if (passwordStrengthCheckDto.password() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new PasswordStrengthCheckResponseDto("Password is required", false, HttpStatus.BAD_REQUEST));
        }
        String password = passwordStrengthCheckDto.password();
        boolean isStrongPassword = memberService.checkPasswordStrength(password);

        if (isStrongPassword) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new PasswordStrengthCheckResponseDto("Strong password", true, HttpStatus.OK));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new PasswordStrengthCheckResponseDto("Weak password", false, HttpStatus.OK));
        }

    }
}
