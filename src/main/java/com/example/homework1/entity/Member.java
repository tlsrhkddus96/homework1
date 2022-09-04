package com.example.homework1.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String id;

    private String email;

    private String password;

    private String nickname;

    private String name;

    private String phone;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){

        roleSet.add(memberRole);

    }

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }

    public void changePhone(String phone){
        this.phone = phone;
    }

    public void changePassword(String password){
        this.password = password;
    }

    /*public void changeID(String id){
        this.id = id;
    }*/



}
