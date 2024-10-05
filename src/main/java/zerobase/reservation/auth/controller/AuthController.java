package zerobase.reservation.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.auth.dto.Login;
import zerobase.reservation.auth.security.JWTUtil;
import zerobase.reservation.auth.service.AuthService;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.member.entity.Member;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JWTUtil jwtUtil;

    /**
     * 매니져 로그인
     * @param login 요청
     * @return 토근 완료 반환
     */

    @PostMapping("/manager")
    public ResponseEntity<?> managerLogin(@RequestBody @Valid Login login) {
        Manager manager = authService.authenticateManager(login);
        String token = jwtUtil.createToken(manager.getEmail(), manager.getMemberType());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body("Login successful");
    }


    /**
     * 사용자 로그인
     * @param login 요청
     * @return 토근 완료 반환
     */

    @PostMapping("/member")
    public ResponseEntity<?> userLogin(@RequestBody @Valid Login login){
        Member member = authService.authenticateCustomer(login);
        String token = jwtUtil.createToken(member.getEmail(), member.getMemberType());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body("Login successful");
    }

}
