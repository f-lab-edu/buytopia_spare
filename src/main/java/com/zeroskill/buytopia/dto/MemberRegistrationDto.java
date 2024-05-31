package com.zeroskill.buytopia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegistrationDto {
    private String memberId;
    private String name;
    private String email;
    private String password;
    private AddressDto address;
}
