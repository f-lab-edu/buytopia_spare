package com.zeroskill.buytopia.entity;

import com.zeroskill.buytopia.dto.AddressDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Column(name = "main_address")
    private String mainAddress;

    @Column(name = "sub_address")
    private String subAddress;

    @Column(name = "zipcode")
    private String zipcode;

    public Address(AddressDto addressDto) {
        this.mainAddress = addressDto.getMainAddress();
        this.subAddress = addressDto.getSubAddress();
        this.zipcode = addressDto.getZipcode();
    }
}
