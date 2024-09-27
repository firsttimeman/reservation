package zerobase.reservation.member.service;

import zerobase.reservation.member.dto.MemberDTO;
import zerobase.reservation.member.dto.RegisterMember;

public interface MemberService {

    MemberDTO register(RegisterMember registerMember);

}
