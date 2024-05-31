package com.zeroskill.buytopia.entity;

import com.zeroskill.buytopia.dto.MemberRegistrationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ColumnDefault("1")
    @Column(name = "grade", nullable = false)
    private Byte grade;

    @Embedded
    private Address address;

    public Member(MemberRegistrationDto memberRegistrationDto, Address address) {
        this.memberId = memberRegistrationDto.getMemberId();
        this.name = memberRegistrationDto.getName();
        this.email = memberRegistrationDto.getEmail();
        this.password = memberRegistrationDto.getPassword();
        this.grade = 1;
        this.address = address;
    }
}
