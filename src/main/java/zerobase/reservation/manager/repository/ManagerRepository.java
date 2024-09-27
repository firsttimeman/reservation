package zerobase.reservation.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.manager.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    boolean existsByEmail(String email);

}
