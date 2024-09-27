package zerobase.reservation.manager.dto;

import lombok.*;
import zerobase.reservation.MemberType;
import zerobase.reservation.manager.entity.Manager;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {
    private Long man_id;
    private String id;
    private String password;
    private String email;
    private String phoneNumber;
    private MemberType memberType;

    public static ManagerDTO fromEntity(Manager manager) {

        return ManagerDTO.builder()
                .man_id(manager.getMan_id())
                .id(manager.getId())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .email(manager.getEmail())
                .memberType(manager.getMemberType())
                .build();

    }

}
