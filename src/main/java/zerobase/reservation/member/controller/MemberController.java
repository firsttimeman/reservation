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

    /**
     * 회원 가입 유저
     * @param member
     * @return
     */


    @PostMapping("/register/member")
    public ResponseEntity<?> register(@RequestBody RegisterMember member) {
        MemberDTO register = memberService.register(member);

        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    /**
     * 회원 정보 조회 (매니져, 유저)
     * @param id
     * @return
     */

    @GetMapping("/member/info")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_PARTNER')")
    public ResponseEntity<?> getinfo(@RequestParam("id") Long id) {
        MemberDTO memberDTO = memberService.memberDetail(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

}
