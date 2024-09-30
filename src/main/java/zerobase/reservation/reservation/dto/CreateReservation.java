package zerobase.reservation.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        private Long storeId;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String username;
        private String userPhoneNumber;
        private String storeName;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDTO reservationDTO) {
            return Response.builder()
                    .username(reservationDTO.getUsername())
                    .userPhoneNumber(reservationDTO.getUserPhoneNumber())
                    .storeName(reservationDTO.getStoreName())
                    .reservationDate(reservationDTO.getReservationDate())
                    .reservationTime(reservationDTO.getReservationTime())
                    .build();
        }


    }






}
