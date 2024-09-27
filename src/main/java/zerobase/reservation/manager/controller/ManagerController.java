package zerobase.reservation.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.manager.dto.ManagerDTO;
import zerobase.reservation.manager.dto.RegisterManager;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.manager.service.ManagerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerRepository managerRepository;


    @PostMapping("/register/manager")
    public ResponseEntity<?> regsiter(@RequestBody RegisterManager manager) {
        ManagerDTO register = managerService.register(manager);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    // TODO 파트너일떄만 검색가능하게 만들기
    @GetMapping("/partner/info")
    public ResponseEntity<?> getPartnerInfo(@RequestParam("id") Long id) {
        ManagerDTO managerDTO = managerService.memberDetail(id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

}
