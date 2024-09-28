package zerobase.reservation.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateReview {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String content;
        private double rating;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Response {
        private Long reviewId;
        private String content;
        private double rating;
        private String username;
        private String storeName;

        public static Response from(ReviewDTO reviewDTO) {

            return Response.builder()
                    .reviewId(reviewDTO.getReviewId())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .username(reviewDTO.getUsername())
                    .storeName(reviewDTO.getStoreName())
                    .build();
        }

    }

}
