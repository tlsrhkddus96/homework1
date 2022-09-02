package com.example.homework1.controller;

import com.example.homework1.dto.MemberDTO;
import com.example.homework1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping("/index")
    public void test(){

        log.info("ㅇㅇ");

    }

    @GetMapping("/member/register")
    public void registerMember(){
        log.info("Get Register");
    }

    @GetMapping({"/member/my","/member/modify"})
    public void myProfile(){

    }


}
