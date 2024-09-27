package zerobase.reservation.manager.service;

import zerobase.reservation.manager.dto.ManagerDTO;
import zerobase.reservation.manager.dto.RegisterManager;

public interface ManagerService {

    ManagerDTO register(RegisterManager registerManager);

    ManagerDTO memberDetail(Long id);

}
