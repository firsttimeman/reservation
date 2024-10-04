package zerobase.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("가게를 찾을수가 없습니다."));


        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을수가 없습니다."));


        LocalTime reservationTime = request.getReservationTime();
        LocalDate reservationDate = request.getReservationDate();
        boolean exists = reservationRepository.existsByReservationDateAndReservationTime
                (reservationDate, reservationTime);

        if(exists) {
            throw new RuntimeException("이미 존재하는 예약입니다.");
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
                .orElseThrow(() -> new RuntimeException("예약이 존재하지 않습니다."));

        ReservationStatus reservationStatus = reservation.getReservationStatus();
        if(reservationStatus.equals(request.getReservationStatus())) {
            throw new RuntimeException("예약 상태가 다릅니다.");
        }

        reservation.setReservationStatus(request.getReservationStatus());

        return ReservationDTO.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> searchReservation(Long id) {

        List<Reservation> reservations = reservationRepository
                .findByStoreManagerManIdOrderByReservationDate(id);

        if(reservations.isEmpty()) {
            throw new RuntimeException("예약이 없습니다.");
        }


        return reservations;
    }

    @Override
    public ReservationDTO updateArrival(Long reservationId, UpdateArrival.Request request) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약이 없습니다."));


        reservation.setArrivalStatus(ArrivalStatus.ARRIVED);
        reservation.setReservationStatus(ReservationStatus.FINISHED);



        if(!reservation.getReservationStatus().equals(ReservationStatus.APPROVE)) {
            throw new RuntimeException("예약 확인 상태 에러");
        }

        if(request.getArrivalTime().toLocalTime().isAfter(reservation.getReservationTime())) {
            throw new RuntimeException("예약시간보다 더 늦었습니다.");
        }


        return ReservationDTO.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약을 찾을수가 없습니다."));

        reservation.setReservationStatus(ReservationStatus.CANCEL);

        return ReservationDTO.fromEntity(reservationRepository.save(reservation));

    }
}
