package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import com.zeroskill.buytopia.dto.MemberResponseDto;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto registerMember(MemberRegistrationDto memberRegistrationDto) {
        Address address = new Address(memberRegistrationDto.getAddress());
        Member member = new Member(memberRegistrationDto, address);
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember);
    }

    public boolean isMemberIdDuplicate(String memberId) {
        return memberRepository.findByMemberId(memberId).isPresent();
    }

    public boolean isEmailDuplicate(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public boolean isMemberIdOrEmailDuplicate(String memberId, String email) {
        return isMemberIdDuplicate(memberId) || isEmailDuplicate(email);
    }

    public boolean checkPasswordStrength(String password) {
        // 정규 표현식 패턴
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        // 패턴과 매치되는지 확인
        return Pattern.compile(regex).matcher(password).matches();
    }
}
