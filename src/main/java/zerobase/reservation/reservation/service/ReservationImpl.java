package zerobase.reservation.reservation.service;

import zerobase.reservation.reservation.dto.CreateReservation;
import zerobase.reservation.reservation.dto.ReservationDTO;
import zerobase.reservation.reservation.dto.UpdateArrival;
import zerobase.reservation.reservation.dto.UpdateReservation;
import zerobase.reservation.reservation.entity.Reservation;

import java.util.List;

public class ReservationImpl implements ReservationService {
    @Override
    public ReservationDTO createReservation(CreateReservation.Request request) {
        return null;
    }

    @Override
    public ReservationDTO updateReservation(Long reservationId, UpdateReservation.Request request) {
        return null;
    }

    @Override
    public List<Reservation> searchReservation(Long id) {
        return List.of();
    }

    @Override
    public ReservationDTO updateArrival(Long reservationId, UpdateArrival.Request request) {
        return null;
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {
        return null;
    }
}
