package com.example.homework1;

import com.example.homework1.entity.Member;
import com.example.homework1.entity.MemberRole;
import com.example.homework1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class memberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testBCrypt(){

        String pwd = "1111";

        String enPwd = passwordEncoder.encode(pwd);

        System.out.println("enPwd = " + enPwd);

        boolean match = passwordEncoder.matches(pwd,enPwd);

        System.out.println("result = "  + match);

    }

    @Test
    public void insertTests(){

        Member member = Member.builder()
                .email("1234@aaa.com")
                .id("1234")
                .password(passwordEncoder.encode("1234"))
                .name("1234")
                .nickname("1234")
                .phone("01012341234")
                .build();

        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);


    }

    @Test
    public void testSelect(){

        String id = "1234";

        System.out.println( memberRepository.findById(id));

    }

}
