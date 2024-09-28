package zerobase.reservation.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.store.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    boolean existsByStoreName(String storeName);

    Optional<Store> findByStoreName(String storeName);

    List<Store> findByManager_ManId(Long manId);

}
