package zerobase.reservation.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecurityController {

    @GetMapping("/exception/accessDenied")
    public ResponseEntity<String> accessDenied() {
        log.info("Access Denied - SecurityController");
        return new ResponseEntity<>("{\"error\": \"Access Denied\", \"message\": \"You do not have permission to access this resource.\"}",
                HttpStatus.FORBIDDEN);
    }

    @GetMapping("/exception/unauthorized")
    public ResponseEntity<String> unAuthorized() {
        log.info("Unauthorized Access - SecurityController");
        return new ResponseEntity<>("{\"error\": \"Unauthorized\", \"message\": \"You are not authorized to access this resource.\"}",
                HttpStatus.UNAUTHORIZED);
    }
}
