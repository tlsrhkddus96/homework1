package com.example.homework1.security.service;

import com.example.homework1.entity.Member;
import com.example.homework1.repository.MemberRepository;
import com.example.homework1.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class DetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("DetailService > 로그인 시도 id / loadUserByUsername : " + username);

        Member result = memberRepository.findById(username);

        if(result == null){
            throw new UsernameNotFoundException("아이디가 없습니다.");
        }

        log.info("id로 조회한 상세내용 : " + result);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                result.getId(),
                result.getPassword(),
                result.getRoleSet().stream().map(role -> new SimpleGrantedAuthority(
                        "ROLE_"+role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setEmail(result.getEmail());
        authMemberDTO.setName(result.getName());
        authMemberDTO.setNickname(result.getNickname());
        authMemberDTO.setPhone(result.getPhone());

        return authMemberDTO;
    }
}
