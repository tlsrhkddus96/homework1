package com.example.homework1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long mno;

    private String id;

    private String email;

    private String password;

    private String nickname;

    private String name;

    private String phone;

}
