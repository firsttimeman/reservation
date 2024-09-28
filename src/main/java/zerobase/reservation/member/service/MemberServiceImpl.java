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
            throw  new RuntimeException("이미 존재하는 유저입니다");
        }

        Member savedMember = memberRepository.save(Member.builder()
                .username(registerMember.getUsername())
                .email(registerMember.getEmail())
                .password(registerMember.getPassword())
                .phoneNumber(registerMember.getPhoneNumber())
                .memberType(MemberType.CUSTOMER)
                .build());


        return MemberDTO.fromEntity(savedMember);
    }

    @Override
    public MemberDTO memberDetail(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저가 없습니다."));

        return MemberDTO.fromEntity(member);
    }
}
