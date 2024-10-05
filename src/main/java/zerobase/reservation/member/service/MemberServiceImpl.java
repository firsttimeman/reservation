package zerobase.reservation.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.global.exception.CustomException;
import zerobase.reservation.member.dto.MemberDTO;
import zerobase.reservation.member.dto.RegisterMember;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;

import static zerobase.reservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static zerobase.reservation.global.type.ErrorCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO register(RegisterMember registerMember) {
        boolean exists = memberRepository.existsByEmail(registerMember.getEmail());

        if(exists) {
            throw new CustomException(ALREADY_EXIST_USER);
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
       .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return MemberDTO.fromEntity(member);
    }
}
