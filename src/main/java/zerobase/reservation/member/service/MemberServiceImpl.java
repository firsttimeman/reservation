package zerobase.reservation.member.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.MemberType;
import zerobase.reservation.member.dto.MemberDTO;
import zerobase.reservation.member.dto.RegisterMember;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO register(RegisterMember registerMember) {
        boolean exists = memberRepository.existsByEmail(registerMember.getEmail());

        if(exists) {
            throw  new RuntimeException("오류입니다");
        }

        Member savedMember = memberRepository.save(Member.builder()
                .id(registerMember.getId())
                .email(registerMember.getEmail())
                .password(registerMember.getPassword())
                .phoneNumber(registerMember.getPhoneNumber())
                .memberType(MemberType.CUSTOMER)
                .build());


        return MemberDTO.fromEntity(savedMember);
    }
}
