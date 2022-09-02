package com.example.homework1.controller;

import com.example.homework1.dto.MemberDTO;
import com.example.homework1.security.dto.AuthMemberDTO;
import com.example.homework1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> MemberRegister(@RequestBody MemberDTO memberDTO){

        String id = memberService.register(memberDTO);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @GetMapping("/load")
    public ResponseEntity<MemberDTO> myPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO){

        String id = authMemberDTO.getId();

        MemberDTO memberDTO = memberService.getMember(id);

        log.info("memberDTO >> " +memberDTO);

        return new ResponseEntity<>(memberDTO,HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO){

        log.info("memberDTO >> " + memberDTO);

        String id = memberService.modify(memberDTO);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody MemberDTO memberDTO){

        String id = memberDTO.getId();

        memberService.remove(id);

        return new ResponseEntity<>(id,HttpStatus.OK);

    }


}
