package zerobase.reservation.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterMember {

    private String id;
    private String password;
    private String email;
    private String phoneNumber;

    public static RegisterMember from(MemberDTO memberDTO) {

        return RegisterMember.builder()
                .id(memberDTO.getId())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .phoneNumber(memberDTO.getPhoneNumber())
                .build();

    }

}
