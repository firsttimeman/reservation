package zerobase.reservation.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.review.dto.CreateReview;
import zerobase.reservation.review.dto.UpdateReview;
import zerobase.reservation.review.service.ReviewService;

@RequestMapping("/api/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public CreateReview.Response createReview(@RequestParam(name = "userId") Long userId,
                                              @RequestParam(name = "storeId") Long storeId,
                                              @RequestParam(name = "reservationId") Long reservationId,
                                              @RequestBody CreateReview.Request request) {

        return CreateReview.Response.from(reviewService.createReview(userId, storeId, reservationId, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_PARTNER')")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

    @PutMapping("/update/{reviewId}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public UpdateReview.Response updateReview(@PathVariable(name = "reviewId") Long reviewId,
                                              @RequestBody UpdateReview.Request request) {

        return UpdateReview.Response.from(reviewService.updateReview(reviewId, request));
    }

}
