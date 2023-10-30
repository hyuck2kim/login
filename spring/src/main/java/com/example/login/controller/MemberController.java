package com.example.login.controller;

import com.example.login.dto.MemberRequestDto;
import com.example.login.dto.MemberResponseDto;
import com.example.login.dto.TokenDto;
import com.example.login.repository.MemberRepository;
import com.example.login.service.AuthService;
import com.example.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        MemberResponseDto responseDto = authService.signup(requestDto);
        String newResourceUri = "api/member/" + responseDto.getId();
        return ResponseEntity.created(URI.create(newResourceUri)).body(responseDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signin(requestDto));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        System.out.println(myInfoBySecurity.getUsername());
        return ResponseEntity.ok((myInfoBySecurity));
    }

}
