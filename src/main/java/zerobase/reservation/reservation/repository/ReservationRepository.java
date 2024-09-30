package zerobase.reservation.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.reservation.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByReservationDateAndReservationTime(LocalDate reservationDate, LocalTime reservationTime);

    List<Reservation>findByStoreManagerManIdOrderByReservationDate(Long storeManagerManId);
}
