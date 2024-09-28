package zerobase.reservation.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.reservation.type.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private ReservationStatus reservationStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDTO reservationDTO) {
            return Response.builder()
                    .reservationId(reservationDTO.getReservationId())
                    .username(reservationDTO.getUsername())
                    .storeName(reservationDTO.getStoreName())
                    .reservationStatus(reservationDTO.getStatus())
                    .reservationDate(reservationDTO.getReservationDate())
                    .reservationTime(reservationDTO.getReservationTime())
                    .build();

        }

    }

}
