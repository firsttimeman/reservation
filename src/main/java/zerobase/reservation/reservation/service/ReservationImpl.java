package zerobase.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.global.exception.CustomException;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;
import zerobase.reservation.reservation.dto.CreateReservation;
import zerobase.reservation.reservation.dto.ReservationDTO;
import zerobase.reservation.reservation.dto.UpdateArrival;
import zerobase.reservation.reservation.dto.UpdateReservation;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.reservation.repository.ReservationRepository;
import zerobase.reservation.reservation.type.ArrivalStatus;
import zerobase.reservation.reservation.type.ReservationStatus;
import zerobase.reservation.store.entity.Store;
import zerobase.reservation.store.repository.StoreRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static zerobase.reservation.global.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReservationImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReservationDTO createReservation(CreateReservation.Request request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));


        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));


        LocalTime reservationTime = request.getReservationTime();
        LocalDate reservationDate = request.getReservationDate();
        boolean exists = reservationRepository.existsByReservationDateAndReservationTime
                (reservationDate, reservationTime);

        if (exists) {
            throw new CustomException(ALREADY_RESERVED);
        }

        Reservation reservation = reservationRepository.save(Reservation.builder()
                .member(member)
                .store(store)
                .reservationStatus(ReservationStatus.WAIT_FOR_APPROVAL)
                .arrivalStatus(ArrivalStatus.READY)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .build());


        return ReservationDTO.fromEntity(reservation);
    }

    @Override
    public ReservationDTO updateReservation(Long reservationId, UpdateReservation.Request request) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        ReservationStatus reservationStatus = reservation.getReservationStatus();
        if (reservationStatus.equals(request.getReservationStatus())) {
            throw new CustomException(RESERVATION_STATUS_CHECK_ERROR);
        }

        reservation.setReservationStatus(request.getReservationStatus());

        return ReservationDTO.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> searchReservation(Long id) {

        List<Reservation> reservations = reservationRepository
                .findByStoreManagerManIdOrderByReservationDate(id);

        if (reservations.isEmpty()) {
            throw new CustomException(RESERVATION_NOT_FOUND);
        }


        return reservations;
    }

    @Override
    public ReservationDTO updateArrival(Long reservationId, UpdateArrival.Request request) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));


        validationReservation(reservation, request.getArrivalTime().toLocalTime());

        reservation.setArrivalStatus(ArrivalStatus.ARRIVED);
        reservation.setReservationStatus(ReservationStatus.FINISHED);



        return ReservationDTO.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        reservation.setReservationStatus(ReservationStatus.CANCEL);

        return ReservationDTO.fromEntity(reservationRepository.save(reservation));

    }

    private void validationReservation(Reservation reservation, LocalTime arrivalTime) {
        if (!reservation.getReservationStatus().equals(ReservationStatus.APPROVE)) {
            throw new CustomException(RESERVATION_STATUS_CHECK_ERROR);
        } else if (arrivalTime.isAfter(reservation.getReservationTime())) {
            throw new CustomException(RESERVATION_TIME_EXCEEDED);
        } else if (arrivalTime.isBefore(reservation.getReservationTime().minusMinutes(10L))) {
            throw new CustomException(CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME);
        }
    }
}
