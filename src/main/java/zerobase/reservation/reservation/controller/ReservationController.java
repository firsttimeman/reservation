package zerobase.reservation.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.reservation.dto.*;
import zerobase.reservation.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 매장 예약
     * @param request
     * @return
     */

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request) {

        return CreateReservation.Response.from(reservationService.createReservation(request));
    }

    /**
     * 예약 승인 및 거부
     * @param id
     * @param request
     * @return
     */

    @PutMapping("/partner/approval/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable("id") Long id,
            @RequestBody UpdateReservation.Request request
    ) {
        return UpdateReservation.Response.from(reservationService.updateReservation(id, request));
    }

    /**
     * 예약 리스트 조회
     * @param id
     * @return
     */


    @GetMapping("/partner/reservation-list/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public SearchReservation getReservation(@PathVariable("id") Long id) {
        return SearchReservation.from(reservationService.searchReservation(id));
    }

    /**
     * 매장 도착 확인 여부 변경
     * @param id
     * @param request
     * @return
     */

    @PutMapping("/changearrive/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public UpdateArrival.Response updateArrival(@PathVariable("id") Long id,
                                                @RequestBody UpdateArrival.Request request) {
        return UpdateArrival.Response.from(reservationService.updateArrival(id, request));
    }

    /**
     * 예약 취소
     * @param id
     * @return
     */


    @DeleteMapping("/cancel")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_PARTNER')")
    public ResponseEntity<?> cancelReservation(@RequestParam("id") Long id) {

        ReservationDTO reservationDTO = reservationService.cancelReservation(id);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }




}
