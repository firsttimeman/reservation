package zerobase.reservation.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterManager {

    private String id;
    private String password;
    private String email;
    private String phoneNumber;

    public static RegisterManager from(ManagerDTO managerDTO){

        return RegisterManager.builder()
                .id(managerDTO.getId())
                .password(managerDTO.getPassword())
                .email(managerDTO.getEmail())
                .phoneNumber(managerDTO.getPhoneNumber())
                .build();

    }

}
