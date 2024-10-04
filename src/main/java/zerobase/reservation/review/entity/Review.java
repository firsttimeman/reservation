package zerobase.reservation.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import zerobase.reservation.BaseEntity;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.store.entity.Store;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(length = 300)
    private String content;

    @Min(0)
    @Max(5)
    @Column(precision = 2)
    private double rating;


   @ManyToOne
   @JoinColumn(name = "member_user_id")
   private Member member;


    @ManyToOne
    @JoinColumn(name = "store_store_id")
    private Store store;


    @ManyToOne
    @JoinColumn(name = "reservation_reservation_id")
    private Reservation reservation;


}
