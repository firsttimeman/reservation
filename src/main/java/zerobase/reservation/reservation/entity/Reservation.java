package zerobase.reservation.reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import zerobase.reservation.global.entity.BaseEntity;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.reservation.type.ArrivalStatus;
import zerobase.reservation.reservation.type.ReservationStatus;
import zerobase.reservation.store.entity.Store;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "member_user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;

    private LocalTime reservationTime;


}
