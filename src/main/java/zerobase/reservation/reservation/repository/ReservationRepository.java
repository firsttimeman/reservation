package zerobase.reservation.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByReservationId(Long id);
}
