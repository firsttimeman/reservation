package zerobase.reservation.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.manager.dto.ManagerDTO;
import zerobase.reservation.manager.dto.RegisterManager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.manager.service.ManagerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 회원 가입 매니져
     * @param manager
     * @return
     */

    @PostMapping("/register/manager")
    public ResponseEntity<?> regsiter(@RequestBody RegisterManager manager) {
        ManagerDTO register = managerService.register(manager);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    /**
     * 회원 정보 조회
     * @param id
     * @return
     */

    @GetMapping("/partner/info")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public ResponseEntity<?> getPartnerInfo(@RequestParam("id") Long id) {
        ManagerDTO managerDTO = managerService.memberDetail(id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

}
