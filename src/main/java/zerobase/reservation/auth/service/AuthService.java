package zerobase.reservation.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reservation.auth.dto.Login;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.global.exception.CustomException;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;

import static zerobase.reservation.auth.type.MemberType.MEMBER;
import static zerobase.reservation.auth.type.MemberType.PARTNER;
import static zerobase.reservation.global.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final ManagerRepository managerRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     *
     * 매니저 정보 확인 비번 확인
     */

    public Manager authenticateManager(Login login) {
        Manager manager = checkManagerEmail(login.getEmail());

        if (!passwordEncoder.matches(login.getPassword(), manager.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        return manager;

    }

    /**
     *
     * 유저 정보 확인 비번 확인
     */

    public Member authenticateCustomer(Login login) {
        Member member = checkUserEmail(login.getEmail());

        if (!this.passwordEncoder.matches(login.getPassword(), member.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        return member;
    }

    /**
     * 이메일을 이용하여 저장소에 이메일과 일치하는지를 찾는 회원
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (managerRepository.existsByEmail(email)) {
            Manager manager = checkManagerEmail(email);

            return createUserDetails(manager.getEmail(),
                    manager.getPassword(), PARTNER);
        } else if (memberRepository.existsByEmail(email)) {
            Member member = checkUserEmail(email);

            return createUserDetails(member.getEmail(),
                    member.getPassword(), MEMBER);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }


    private UserDetails createUserDetails(String username, String password, MemberType memberType) {
        return User.withUsername(username)
                .password(this.passwordEncoder.encode(password))
                .roles(String.valueOf(memberType))
                .build();
    }


    private Manager checkManagerEmail(String email) {
        return this.managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));
    }

    private Member checkUserEmail(String email) {
        return this.memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

}
