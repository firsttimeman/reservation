package zerobase.reservation.member.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import zerobase.reservation.MemberType;
import zerobase.reservation.member.entity.Member;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long user_id;
    private String id;
    private String password;
    private String phoneNumber;
    private String email;
    private MemberType memberType;


    public static MemberDTO fromEntity(Member member) {
       return MemberDTO.builder()
                .user_id(member.getUser_id())
                .id(member.getId())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .memberType(member.getMemberType())
                .build();
    }

}
