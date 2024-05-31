package com.zeroskill.buytopia.dto;

import com.zeroskill.buytopia.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberResponseDto {
    private Long id;
    private String memberId;
    private String name;
    private String email;
    private String mainAddress;
    private String subAddress;
    private String zipcode;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.mainAddress = member.getAddress().getMainAddress();
        this.subAddress = member.getAddress().getSubAddress();
        this.zipcode = member.getAddress().getZipcode();
    }
}