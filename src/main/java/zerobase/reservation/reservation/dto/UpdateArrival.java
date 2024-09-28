package zerobase.reservation.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zerobase.reservation.reservation.type.ArrivalStatus;
import zerobase.reservation.reservation.type.ReservationStatus;

import java.time.LocalDateTime;

public class UpdateArrival {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String username;
        private String phoneNumber;
        private LocalDateTime arrivalTime;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;

        private ReservationStatus status;
        private ArrivalStatus arrivalStatus;

        public static Response from(ReservationDTO reservationDTO) {

            return Response.builder()
                    .reservationId(reservationDTO.getReservationId())
                    .username(reservationDTO.getUsername())
                    .storeName(reservationDTO.getStoreName())
                    .status(reservationDTO.getStatus())
                    .arrivalStatus(reservationDTO.getArrivalStatus())
                    .build();

        }


    }


}
