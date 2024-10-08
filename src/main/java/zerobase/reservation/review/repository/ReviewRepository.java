package zerobase.reservation.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.reservation.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByReservation_ReservationId(Long reservationId);
}
