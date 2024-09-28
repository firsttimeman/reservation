package zerobase.reservation.reservation.dto;

import lombok.*;
import org.springframework.web.bind.annotation.BindParam;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.reservation.type.ArrivalStatus;
import zerobase.reservation.reservation.type.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long reservationId;
    private String username;
    private String userPhoneNumber;
    private String storeName;

    private ReservationStatus status;
    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public static ReservationDTO fromEntity(Reservation reservation) {
        return ReservationDTO.builder()
                .reservationId(reservation.getReservationId())
                .username(reservation.getMember().getUsername())
                .userPhoneNumber(reservation.getMember().getPhoneNumber())
                .storeName(reservation.getStore().getStoreName())
                .status(reservation.getReservationStatus())
                .arrivalStatus(reservation.getArrivalStatus())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();

    }

}
