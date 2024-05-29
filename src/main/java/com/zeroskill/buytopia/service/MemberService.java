package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import com.zeroskill.buytopia.dto.MemberResponseDto;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
