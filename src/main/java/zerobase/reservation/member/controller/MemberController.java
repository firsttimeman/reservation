package zerobase.reservation.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    // TODO 유저정보 검색기능 구현 시큐리티로 구현
    @GetMapping("/member/info")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_PARTNER')")
    public ResponseEntity<?> getinfo(@RequestParam("id") Long id) {
        MemberDTO memberDTO = memberService.memberDetail(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

}
