package zerobase.reservation.manager.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.manager.dto.ManagerDTO;
import zerobase.reservation.manager.dto.RegisterManager;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements  ManagerService{

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ManagerDTO register(RegisterManager registerManager) {
        boolean exists = managerRepository.existsByEmail(registerManager.getEmail());
        if(exists) {
            throw new RuntimeException("유저가 존재합니다");
        }

        registerManager.setPassword(passwordEncoder.encode(registerManager.getPassword()));

        Manager savedManager = managerRepository.save(Manager.builder()
                .username(registerManager.getUsername())
                .email(registerManager.getEmail())
                .password(registerManager.getPassword())
                .phoneNumber(registerManager.getPhoneNumber())
                .memberType(MemberType.PARTNER)
                .build());

        return ManagerDTO.fromEntity(savedManager);
    }

    @Override
    public ManagerDTO memberDetail(Long id) {
        Manager manager = managerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("사용자가 없음"));

        return ManagerDTO.fromEntity(manager);
    }
}
