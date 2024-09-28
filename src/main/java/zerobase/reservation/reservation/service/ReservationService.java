package zerobase.reservation.reservation.service;

import zerobase.reservation.reservation.dto.CreateReservation;
import zerobase.reservation.reservation.dto.ReservationDTO;
import zerobase.reservation.reservation.dto.UpdateArrival;
import zerobase.reservation.reservation.dto.UpdateReservation;
import zerobase.reservation.reservation.entity.Reservation;

import java.util.List;
public interface ReservationService {

    ReservationDTO createReservation(CreateReservation.Request request);

    ReservationDTO updateReservation(Long reservationId, UpdateReservation.Request request);

    List<Reservation> searchReservation(Long id);

    ReservationDTO updateArrival(Long reservationId, UpdateArrival.Request request);

    ReservationDTO cancelReservation(Long reservationId);
}
