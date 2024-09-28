package zerobase.reservation.store.service;

import zerobase.reservation.store.dto.CreateStore;
import zerobase.reservation.store.dto.StoreDTO;
import zerobase.reservation.store.dto.UpdateStore;

import java.util.List;

public interface StoreService{

    StoreDTO createStore(CreateStore.Request request);

    void deleteStore(Long managerId, Long storeId);

    StoreDTO updateStore(Long id, UpdateStore.Request request);

    StoreDTO detailStore(String name);

    List<StoreDTO> searchStoreList(Long id);
}
