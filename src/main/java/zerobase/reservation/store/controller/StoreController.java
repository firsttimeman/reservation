package zerobase.reservation.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.store.dto.CreateStore;
import zerobase.reservation.store.dto.UpdateStore;
import zerobase.reservation.store.service.StoreService;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @PostMapping("/partner/create")
    public CreateStore.Response createStore(@RequestBody CreateStore.Request request) {
         return CreateStore.Response.from(storeService.createStore(request));
    }

    @PutMapping("/partner/update/{id}")
    public UpdateStore.Response updateStore(@PathVariable("id") Long id,
                                            @RequestBody UpdateStore.Request request) {
        return UpdateStore.Response.from(storeService.updateStore(id, request));
    }

    @DeleteMapping("/partner/delete")
    public ResponseEntity<?> deleteStore(@RequestParam(name = "id") Long managerId,
                                         @RequestParam(name = "store") Long storeId) {
        storeService.deleteStore(managerId, storeId);
        return  ResponseEntity.ok("매장 삭제가 완료되었습니다.");
    }

    @GetMapping("/detail/{storeName}")
    public ResponseEntity<?> storeDetail(@PathVariable("storeName") String storeName) {
        return new ResponseEntity<>(storeService.detailStore(storeName), HttpStatus.OK);
    }

    @GetMapping("/partner/list")
    public ResponseEntity<?> getStoreList(@RequestParam("managerId") Long id) {
        return new ResponseEntity<>(storeService.searchStoreList(id), HttpStatus.OK);
    }





}
