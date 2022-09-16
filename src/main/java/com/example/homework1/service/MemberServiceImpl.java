package com.example.homework1.service;

import com.example.homework1.dto.MemberDTO;
import com.example.homework1.encrypt.Encrypt;
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
    public String register(MemberDTO memberDTO) throws Exception {

        String enPwd = passwordEncoder.encode(memberDTO.getPassword());
        String enEmail = Encrypt.encryptAES256(memberDTO.getEmail());

        memberDTO.setPassword(enPwd);
        memberDTO.setEmail(enEmail);

        log.info("/MemberServiceImpl  MemberDTO :  " + memberDTO);

        Member member = dtoToEntity(memberDTO);
        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

        return memberDTO.getId();
    }

    @Override
    public MemberDTO getMember(String id) throws Exception {

        Member member = memberRepository.findById(id);

        MemberDTO memberDTO = entityToDTO(member);

        String decodedEmail = Encrypt.decryptAES256(memberDTO.getEmail());
        memberDTO.setEmail(decodedEmail);

        return memberDTO;
    }

    @Transactional
    @Override
    public String modify(MemberDTO memberDTO) {

        String email = memberDTO.getEmail();

        Member member = memberRepository.findByEmail(email);

     //   member.changeID(memberDTO.getId());
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

    @Override
    public String checkIdAndEmail(String id, String email) {

        Member member = memberRepository.findById(id);

        if(member == null){
            return "존재하지 않는 ID 입니다.";
        }

        String memberEmail = member.getEmail();
        String memberId = member.getId();

        boolean checkEmail = memberEmail.equals(email);
        boolean checkId = memberId.equals(id);

        if(checkEmail && checkId){
            return "확인";
        }else {
            return "이메일 또는 아이디가 정확하지 않습니다.";
        }
    }

    @Transactional
    @Override
    public String changePwd(MemberDTO memberDTO) {

        String enPwd = passwordEncoder.encode(memberDTO.getPassword());

        Member member = memberRepository.findById(memberDTO.getId());

        member.changePassword(enPwd);

        return member.getId();
    }

}
