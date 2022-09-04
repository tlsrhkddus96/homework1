package com.example.homework1.service;

import com.example.homework1.dto.MemberDTO;
import com.example.homework1.entity.Member;

public interface MemberService {

    String register(MemberDTO memberDTO);

    MemberDTO getMember(String id);

    String modify(MemberDTO memberDTO);

    void remove(String id);

    String checkIdAndEmail(String id, String email);

    String changePwd(MemberDTO memberDTO);

    default MemberDTO entityToDTO(Member member) {

        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .name(member.getName())
                .password(member.getPassword())
                .phone(member.getPhone())
                .email(member.getEmail())
                .build();

        return memberDTO;
    }

    default Member dtoToEntity(MemberDTO memberDTO){

        Member member = Member.builder()
                .id(memberDTO.getId())
                .email(memberDTO.getEmail())
                .nickname(memberDTO.getNickname())
                .name(memberDTO.getName())
                .password(memberDTO.getPassword())
                .phone(memberDTO.getPhone())
                .build();

        return member;
    }

}
