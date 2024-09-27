package zerobase.reservation.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.member.dto.MemberDTO;
import zerobase.reservation.member.dto.RegisterMember;
import zerobase.reservation.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {


    private final MemberService memberService;


    @PostMapping("/register/member")
    public ResponseEntity<?> register(@RequestBody RegisterMember member) {
        MemberDTO register = memberService.register(member);

        return ResponseEntity.ok(register);
    }

    // TODO 유저정보 검색기능 구현 시큐리티로 구현

}
