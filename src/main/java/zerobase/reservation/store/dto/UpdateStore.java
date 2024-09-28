package zerobase.reservation.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateStore {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private Long managerId;


        private String storeName;


        private String location;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String storeName;
        private String location;

        public static Response from(StoreDTO storeDto) {
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .location(storeDto.getLocation())
                    .build();
        }
    }



}
