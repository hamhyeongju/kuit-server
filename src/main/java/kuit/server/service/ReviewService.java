package kuit.server.service;

import kuit.server.dao.ReviewDao;
import kuit.server.dto.review.GetRestaurantReviewDto;
import kuit.server.dto.review.GetUserReviewDto;
import kuit.server.dto.review.PostUserReviewRequest;
import kuit.server.dto.review.PostUserReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;

    public List<GetUserReviewDto> findUserReviewList(Long userId) {
        List<GetUserReviewDto> reviewList = reviewDao.findUserReviewList(userId);
        return reviewList;
    }

    public List<GetRestaurantReviewDto> findRestaurantReviewList(Long restaurantId) {
        List<GetRestaurantReviewDto> reviewList = reviewDao.findRestaurantReviewList(restaurantId);
        return reviewList;
    }

    public PostUserReviewResponse createUserReview(PostUserReviewRequest postUserReviewRequest) {
        Long reviewId = reviewDao.createUserReview(postUserReviewRequest);
        return new PostUserReviewResponse(reviewId);
    }

}
