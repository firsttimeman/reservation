package zerobase.reservation.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.store.dto.CreateStore;
import zerobase.reservation.store.dto.UpdateStore;
import zerobase.reservation.store.service.StoreService;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 매장 등록
     * @param request
     * @return
     */


    @PostMapping("/partner/create")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public CreateStore.Response createStore(@RequestBody CreateStore.Request request) {
         return CreateStore.Response.from(storeService.createStore(request));
    }

    /**
     * 매장 내용 수정
     * @param id
     * @param request
     * @return
     */

    @PutMapping("/partner/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public UpdateStore.Response updateStore(@PathVariable("id") Long id,
                                            @RequestBody UpdateStore.Request request) {
        return UpdateStore.Response.from(storeService.updateStore(id, request));
    }

    /**
     * 매장 삭제
     * @param managerId
     * @param storeId
     * @return
     */

    @DeleteMapping("/partner/delete")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public ResponseEntity<?> deleteStore(@RequestParam(name = "id") Long managerId,
                                         @RequestParam(name = "store") Long storeId) {
        storeService.deleteStore(managerId, storeId);
        return  ResponseEntity.ok("매장 삭제가 완료되었습니다.");
    }

    /**
     * 매장 정보 확인
     * @param storeName
     * @return
     */

    @GetMapping("/detail/{storeName}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public ResponseEntity<?> storeDetail(@PathVariable("storeName") String storeName) {
        return new ResponseEntity<>(storeService.detailStore(storeName), HttpStatus.OK);
    }

    /**
     * 매장 리스트 조회
     * @param id
     * @return
     */

    @GetMapping("/partner/list")
    @PreAuthorize("hasAnyRole('ROLE_PARTNER')")
    public ResponseEntity<?> getStoreList(@RequestParam("managerId") Long id) {
        return new ResponseEntity<>(storeService.searchStoreList(id), HttpStatus.OK);
    }





}
