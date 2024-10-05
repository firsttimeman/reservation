package zerobase.reservation.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.reservation.global.exception.CustomException;
import zerobase.reservation.manager.entity.Manager;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.store.dto.CreateStore;
import zerobase.reservation.store.dto.StoreDTO;
import zerobase.reservation.store.dto.UpdateStore;
import zerobase.reservation.store.entity.Store;
import zerobase.reservation.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

import static zerobase.reservation.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    @Override
    public StoreDTO createStore(CreateStore.Request request) {

        Manager manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if(storeRepository.existsByStoreName(request.getStoreName())) {
            throw new CustomException(ALREADY_EXIST_STORE);
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
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        if(!store.getManager().getManId().equals(managerId)) {
            throw new CustomException(STORE_NOT_MATCH_MANAGER);
        }

        storeRepository.delete(store);


    }

    @Override
    public StoreDTO updateStore(Long id, UpdateStore.Request request) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
        log.info("Store manager ID: " + store.getManager().getManId());
        log.info("Passed manager ID: " +id);

        if(!store.getManager().getManId().equals(request.getManagerId())) {
            throw new CustomException(STORE_NOT_MATCH_MANAGER);
        }

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());


        return StoreDTO.fromEntity(storeRepository.save(store));
    }

    @Override
    public StoreDTO detailStore(String name) {

        Store store = storeRepository.findByStoreName(name)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));


        return StoreDTO.fromEntity(store);
    }

    @Override
    public List<StoreDTO> searchStoreList(Long id) {

        List<Store> storeList = storeRepository.findByManager_ManId(id);

        if(storeList.isEmpty()) {
            throw new CustomException(STORE_NOT_FOUND);
        }

        return storeList.stream()
                .map(a -> StoreDTO.fromEntity(a))
                .collect(Collectors.toList());
    }
}
