package zerobase.reservation.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.global.type.ErrorCode;
import zerobase.reservation.global.exception.CustomException;

@Slf4j
@RestController
public class SecurityController {

    //403 반환
    @GetMapping("/exception/accessDenied")
    public ResponseEntity<String> accessDenied() {
        log.info("INVALID_ACCESS_TOKEN - SecurityController");
        throw new CustomException(ErrorCode.INVALID_ACCESS_TOKEN);
    }

    //401 반환
    @GetMapping("/exception/unauthorized")
    public ResponseEntity<String> unAuthorized() {
        log.info("JWT_TOKEN_WRONG_TYPE - SecurityController");
        throw new CustomException(ErrorCode.JWT_TOKEN_WRONG_TYPE);
    }
}
