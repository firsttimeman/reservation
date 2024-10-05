package zerobase.reservation.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.member.entity.Member;
import zerobase.reservation.member.repository.MemberRepository;
import zerobase.reservation.reservation.entity.Reservation;
import zerobase.reservation.reservation.repository.ReservationRepository;
import zerobase.reservation.reservation.type.ReservationStatus;
import zerobase.reservation.review.dto.CreateReview;
import zerobase.reservation.review.dto.ReviewDTO;
import zerobase.reservation.review.dto.UpdateReview;
import zerobase.reservation.review.entity.Review;
import zerobase.reservation.review.repository.ReviewRepository;
import zerobase.reservation.store.entity.Store;
import zerobase.reservation.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;



    @Override
    public ReviewDTO createReview(Long userId, Long storeId, Long reservationId, CreateReview.Request request) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약번호가 존재하지 않습니다"));

        validationReviewStatus(reservation, member);

        Review save = reviewRepository.save(Review.builder()
                .content(request.getContent())
                .member(member)
                .store(store)
                .reservation(reservation)
                .rating(request.getRating())
                .build());



        return ReviewDTO.fromEntity(save);
    }

    private void validationReviewStatus(Reservation reservation, Member member) {
        if(!reservation.getMember().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("유저가 존재하지 않습니다.");
        }

        if(reviewRepository.existsByReservation_ReservationId(reservation.getReservationId())) {
            throw new RuntimeException("이미 존재하는 리뷰입니다.");
        }

        if(!reservation.getReservationStatus().equals(ReservationStatus.FINISHED)) {
            throw new RuntimeException("리뷰를 작성할수가 없습니다.");
        }
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.delete(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다.")));

    }

    @Override
    public ReviewDTO updateReview(Long reviewId, UpdateReview.Request request) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을수가 없습니다."));

        if (request.getRating() > 5 || request.getRating() < 0) {
            throw new RuntimeException("리뷰의 별점이 잘못됬습니다.");
        }
        if (request.getContent().length() > 300) {
            throw new RuntimeException("리뷰의 텍스트가 300자 넘어갑니다.");
        }

        review.setRating(request.getRating());
        review.setContent(request.getContent());
        Review save = reviewRepository.save(review);

        return ReviewDTO.fromEntity(save);
    }
}
