package zerobase.reservation.store.entity;

import jakarta.persistence.*;
import lombok.*;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.review.entity.Review;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @OneToOne
    @JoinColumn(name = "manager_man_id")
    private Manager manager;

    private String storeName;

    private String location;

    private String phoneNumber;


    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Reservation> reservationList = new ArrayList<>();





}
