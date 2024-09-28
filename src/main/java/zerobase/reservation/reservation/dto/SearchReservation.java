package zerobase.reservation.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zerobase.reservation.reservation.entity.Reservation;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchReservation {

    private List<ReservationDTO> reservationList;

    public static SearchReservation from(List<Reservation> reservationList) {
        List<ReservationDTO> reservationDTOList = reservationList.stream()
                .map(reservation -> ReservationDTO.fromEntity(reservation))
                .collect(Collectors.toList());

        return new SearchReservation(reservationDTOList);
    }
}
