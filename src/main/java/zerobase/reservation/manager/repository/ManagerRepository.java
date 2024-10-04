package zerobase.reservation.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.manager.entity.Manager;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    boolean existsByEmail(String email);
    Optional<Manager> findByEmail(String email);
}
