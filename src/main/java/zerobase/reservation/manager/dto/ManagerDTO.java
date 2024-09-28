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
    private Long manId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private MemberType memberType;

    public static ManagerDTO fromEntity(Manager manager) {

        return ManagerDTO.builder()
                .manId(manager.getManId())
                .username(manager.getUsername())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .email(manager.getEmail())
                .memberType(manager.getMemberType())
                .build();

    }

}
