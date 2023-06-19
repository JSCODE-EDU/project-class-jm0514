package com.toyboard.jeongmin.member.controller;

import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.dto.*;
import com.toyboard.jeongmin.member.jwt.Login;
import com.toyboard.jeongmin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberRequest memberRequest){
        return ResponseEntity.ok(memberService.signUp(memberRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> logIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(memberService.logIn(loginRequest));
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> findUserInfo(@Login Member member) {
        return ResponseEntity.ok(memberService.findUserInfo(member));
    }

}


