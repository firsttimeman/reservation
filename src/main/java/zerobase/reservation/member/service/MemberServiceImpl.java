package zerobase.reservation.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.member.dto.MemberDTO;
import zerobase.reservation.member.dto.RegisterMember;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO register(RegisterMember registerMember) {
        boolean exists = memberRepository.existsByEmail(registerMember.getEmail());

        if(exists) {
            throw  new RuntimeException("이미 존재하는 유저입니다");
        }

        registerMember.setPassword(passwordEncoder.encode(registerMember.getPassword()));

        Member savedMember = memberRepository.save(Member.builder()
                .username(registerMember.getUsername())
                .email(registerMember.getEmail())
                .password(registerMember.getPassword())
                .phoneNumber(registerMember.getPhoneNumber())
                .memberType(MemberType.MEMBER)
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
