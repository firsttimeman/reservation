package zerobase.reservation.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zerobase.reservation.review.entity.Review;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private String content;
    private double rating;
    private String username;
    private String storeName;

    public static ReviewDTO fromEntity(Review review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .rating(review.getRating())
                .username(review.getMember().getUsername())
                .storeName(review.getStore().getStoreName())
                .build();
    }

}
