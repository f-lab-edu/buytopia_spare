package com.zeroskill.buytopia.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeroskill.buytopia.controller.MemberController;
import com.zeroskill.buytopia.converter.MemberResponseConverter;
import com.zeroskill.buytopia.dto.AddressDto;
import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import com.zeroskill.buytopia.dto.MemberResponseDto;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MemberController.class) // YourController만 로드하여 테스트합니다.
public class MemberControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberResponseConverter memberResponseConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterMember() throws Exception {
        String name = "zeroskill2400";
        String email = "zeroskill2400@gmail.com";
        String password = "zeroskill2400";
        String mainAddress = "서울특별시 관악구 서울대학교";
        String subAddress = "304호";
        String zipcode = "12345";
        AddressDto addressDto = new AddressDto(mainAddress, subAddress, zipcode);
        MemberRegistrationDto memberRegistrationDto = new MemberRegistrationDto(name, email, password, addressDto);

        Address address = new Address(addressDto);
        Member member = new Member(memberRegistrationDto, address);

        MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        ResponseEntity<MemberResponseDto> responseEntity = ResponseEntity.ok().body(memberResponseDto);

        given(memberService.registerMember(any(MemberRegistrationDto.class))).willReturn(memberResponseDto);

        given(memberResponseConverter.convertToResponseEntity(any(MemberResponseDto.class))).willReturn(responseEntity);

        String requestBody = objectMapper.writeValueAsString(memberRegistrationDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/members/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.mainAddress").value(mainAddress))
                .andExpect(jsonPath("$.subAddress").value(subAddress))
                .andExpect(jsonPath("$.zipcode").value(zipcode));
    }
}
