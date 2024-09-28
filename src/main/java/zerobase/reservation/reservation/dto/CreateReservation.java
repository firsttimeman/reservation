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




}
