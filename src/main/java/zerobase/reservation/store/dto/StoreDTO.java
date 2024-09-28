package zerobase.reservation.store.dto;

import lombok.*;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.store.entity.Store;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Manager manager;
    private String storeName;
    private String location;
    private String phoneNumber;

    public static StoreDTO fromEntity(Store store) {
        return StoreDTO.builder()
                .manager(store.getManager())
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }


}
