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
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signin(requestDto));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        System.out.println(myInfoBySecurity.getNickname());
        return ResponseEntity.ok((myInfoBySecurity));
    }

//    @PostMapping("/signup")
//    public @ResponseBody ResponseEntity<String> signup(@RequestBody SignupRequestDto request) {
//        Member member = request.toUser();
//        System.out.println("회원가입 진행 : " + member);
//        String rawPassword = member.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        member.setPassword(encPassword);
////        member.setRole("ROLE_USER");
//        memberRepository.save(member);
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body("회원 가입이 성공적으로 완료되었습니다.");
//    }
}
