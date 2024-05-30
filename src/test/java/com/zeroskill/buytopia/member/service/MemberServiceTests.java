package com.zeroskill.buytopia.member.service;

import com.zeroskill.buytopia.dto.AddressDto;
import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import com.zeroskill.buytopia.dto.MemberResponseDto;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.repository.MemberRepository;
import com.zeroskill.buytopia.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTests {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository; // 가짜 레포지토리

    @Test
    void testRegisterMember() {
        String mainAddress = "서울특별시 관악구 서울대학교";
        String subAddress = "304호";
        String zipcode = "08728";

        String name = "김범수";
        String email = "zeroskill2400@gmail.com";
        String password = "abcd1234";

        AddressDto addressDto = new AddressDto(mainAddress, subAddress, zipcode);
        MemberRegistrationDto memberRegistrationDto = new MemberRegistrationDto(name, email, password, addressDto);

        Address address = new Address(addressDto);
        Member member = new Member(memberRegistrationDto, address);

        given(memberRepository.save(any(Member.class))).willReturn(member);

        MemberResponseDto memberResponseDto = memberService.registerMember(memberRegistrationDto);
        assertNotNull(memberResponseDto.getName());
        assertNotNull(memberResponseDto.getEmail());
        assertNotNull(memberResponseDto.getMainAddress());
        assertNotNull(memberResponseDto.getSubAddress());
        assertNotNull(memberResponseDto.getZipcode());

        assertEquals(name, memberResponseDto.getName());
        assertEquals(email, memberResponseDto.getEmail());
        assertEquals(mainAddress, memberResponseDto.getMainAddress());
        assertEquals(subAddress, memberResponseDto.getSubAddress());
        assertEquals(zipcode, memberResponseDto.getZipcode());

        verify(memberRepository).save(any(Member.class));
    }
}
