package com.example.homework1.service;

import com.example.homework1.dto.MemberDTO;
import com.example.homework1.entity.Member;
import com.example.homework1.entity.MemberRole;
import com.example.homework1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    @Override
    public String register(MemberDTO memberDTO) {

        String enPwd = passwordEncoder.encode(memberDTO.getPassword());

        memberDTO.setPassword(enPwd);

        log.info("/MemberServiceImpl  MemberDTO :  " + memberDTO);

        Member member = dtoToEntity(memberDTO);
        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

        return memberDTO.getId();
    }

    @Override
    public MemberDTO getMember(String id) {

        Member member = memberRepository.findById(id);

        MemberDTO memberDTO = entityToDTO(member);

        return memberDTO;
    }

    @Transactional
    @Override
    public String modify(MemberDTO memberDTO) {

        String email = memberDTO.getEmail();

        Member member = memberRepository.findByEmail(email);

        member.changeID(memberDTO.getId());
        member.changeNickname(memberDTO.getNickname());
        member.changePhone(memberDTO.getPhone());

        log.info("Changed Member >> " + member);

        memberRepository.save(member);

        return member.getId();
    }

    @Transactional
    @Override
    public void remove(String id) {

        memberRepository.deleteById(id);

    }


}
