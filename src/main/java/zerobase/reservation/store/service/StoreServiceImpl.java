package zerobase.reservation.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.store.dto.CreateStore;
import zerobase.reservation.store.dto.StoreDTO;
import zerobase.reservation.store.dto.UpdateStore;
import zerobase.reservation.store.entity.Store;
import zerobase.reservation.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    @Override
    public StoreDTO createStore(CreateStore.Request request) {

        Manager manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("유저가 없음"));

        if(storeRepository.existsByStoreName(request.getStoreName())) {
            throw new RuntimeException("이미 존재하는 가게입니다");
        }

        return StoreDTO.fromEntity(storeRepository.save(Store.builder()
                .manager(manager)
                .storeName(request.getStoreName())
                .location(request.getLocation())
                .phoneNumber(request.getPhoneNumber())
                .build()));

    }

    @Override
    public void deleteStore(Long managerId, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 식당"));

        if(!store.getManager().getManId().equals(managerId)) {
            throw new RuntimeException("가게 알맞지 않습니다.");
        }

        storeRepository.delete(store);


    }

    @Override
    public StoreDTO updateStore(Long id, UpdateStore.Request request) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("매장이 존재하지 않습니다."));
        log.info("Store manager ID: " + store.getManager().getManId());
        log.info("Passed manager ID: " +id);

        if(!store.getManager().getManId().equals(request.getManagerId())) {
            throw new RuntimeException("매장이 맞지 않습니다.");
        }

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());


        return StoreDTO.fromEntity(storeRepository.save(store));
    }

    @Override
    public StoreDTO detailStore(String name) {

        Store store = storeRepository.findByStoreName(name)
                .orElseThrow(() -> new RuntimeException("매장이 없습니다"));


        return StoreDTO.fromEntity(store);
    }

    @Override
    public List<StoreDTO> searchStoreList(Long id) {

        List<Store> storeList = storeRepository.findByManager_ManId(id);

        if(storeList.isEmpty()) {
            throw new RuntimeException("가게가 존재하지 않습니다.");
        }

        return storeList.stream()
                .map(a -> StoreDTO.fromEntity(a))
                .collect(Collectors.toList());
    }
}
