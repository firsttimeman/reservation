package zerobase.reservation.member.dto;

import lombok.*;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.member.entity.Member;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private MemberType memberType;


    public static MemberDTO fromEntity(Member member) {
        return MemberDTO.builder()
                .userId(member.getUserId())
                .username(member.getUsername())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .memberType(member.getMemberType())
                .build();
    }

}
