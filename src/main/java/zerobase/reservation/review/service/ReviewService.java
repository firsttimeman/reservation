package zerobase.reservation.review.service;

import zerobase.reservation.review.dto.CreateReview;
import zerobase.reservation.review.dto.ReviewDTO;
import zerobase.reservation.review.dto.UpdateReview;

public interface ReviewService {


    ReviewDTO createReview(Long userId, Long storeId, Long reservationId,
                           CreateReview.Request request);

    void deleteReview(Long reviewId);

    ReviewDTO updateReview(Long reviewId, UpdateReview.Request request);

}
