package zerobase.reservation.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.auth.dto.Login;
import zerobase.reservation.auth.security.JWTUtil;
import zerobase.reservation.auth.service.AuthService;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.member.entity.Member;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @PostMapping("/manager")
    public ResponseEntity<?> managerLogin(@RequestBody @Valid Login login) {
        Manager manager = authService.authenticateManager(login);
        String token = jwtUtil.createToken(manager.getEmail(), manager.getMemberType());
        return ResponseEntity.ok(Map.of("token", "Bearer " + token));
    }


    @PostMapping("/member")
    public ResponseEntity<?> userLogin(@RequestBody @Valid Login login){
        Member member = this.authService.authenticateCustomer(login);
        String token = jwtUtil.createToken(member.getEmail(), member.getMemberType());
        return ResponseEntity.ok(Map.of("token", "Bearer " + token));
    }

}
