package zerobase.reservation.manager.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.global.exception.CustomException;
import zerobase.reservation.manager.dto.ManagerDTO;
import zerobase.reservation.manager.dto.RegisterManager;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;

import static zerobase.reservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static zerobase.reservation.global.type.ErrorCode.MANAGER_NOT_FOUND;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public ManagerDTO register(RegisterManager registerManager) {
        boolean exists = managerRepository.existsByEmail(registerManager.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXIST_USER);
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
        orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));

        return ManagerDTO.fromEntity(manager);
    }
}
