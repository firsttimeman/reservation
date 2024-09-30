package zerobase.reservation.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.reservation.dto.*;
import zerobase.reservation.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request) {

        return CreateReservation.Response.from(reservationService.createReservation(request));
    }

    @PutMapping("/partner/approval/{id}")
    public UpdateReservation.Response updateReservation(
            @PathVariable("id") Long id,
            @RequestBody UpdateReservation.Request request
    ) {
        return UpdateReservation.Response.from(reservationService.updateReservation(id, request));
    }


    @GetMapping("/partner/reservation-list/{id}")
    public SearchReservation getReservation(@PathVariable("id") Long id) {
        return SearchReservation.from(reservationService.searchReservation(id));
    }

    @PutMapping("/changearrive/{id}")
    public UpdateArrival.Response updateArrival(@PathVariable("id") Long id,
                                                @RequestBody UpdateArrival.Request request) {
        return UpdateArrival.Response.from(reservationService.updateArrival(id, request));
    }

    //TODO 다시 생각해보기
    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelReservation(@RequestParam("id") Long id) {

        ReservationDTO reservationDTO = reservationService.cancelReservation(id);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }




}
