package zerobase.reservation.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
    WAIT_FOR_APPROVAL("승인 대기"),
    APPROVE("승인 완료"),
    CANCEL("승인 취소"),
    FINISHED("과정 완료");


    private final String description;
}
