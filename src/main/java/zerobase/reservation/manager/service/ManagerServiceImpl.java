package zerobase.reservation.manager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.MemberType;
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

    @Override
    public ManagerDTO register(RegisterManager registerManager) {
        boolean exists = managerRepository.existsByEmail(registerManager.getEmail());
        if(exists) {
            throw new RuntimeException("유저가 존재합니다");
        }

        Manager savedManager = managerRepository.save(Manager.builder()
                .id(registerManager.getId())
                .password(registerManager.getPassword())
                .email(registerManager.getEmail())
                .phoneNumber(registerManager.getPhoneNumber())
                .memberType(MemberType.MANAGER)
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
