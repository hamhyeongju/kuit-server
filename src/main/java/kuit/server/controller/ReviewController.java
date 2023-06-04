package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.review.*;
import kuit.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/users/{userId}/reviews")
    public BaseResponse<List<GetUserReviewDto>> findUserReviewList(@PathVariable Long userId) {

        List<GetUserReviewDto> reviewDtoList = reviewService.findUserReviewList(userId);
        BaseResponse<List<GetUserReviewDto>> response = new BaseResponse<>(reviewDtoList);
        return response;
    }

    @GetMapping("/restaurants/{restaurantId}/reviews")
    public BaseResponse<List<GetRestaurantReviewDto>> findRestaurantReviewList(@PathVariable Long restaurantId) {

        List<GetRestaurantReviewDto> reviewDtoList = reviewService.findRestaurantReviewList(restaurantId);
        BaseResponse<List<GetRestaurantReviewDto>> response = new BaseResponse<>(reviewDtoList);
        return response;
    }

    @PostMapping("/users/{userId}/restaurants/{restaurantId}/reviews")
    public BaseResponse<PostUserReviewResponse> createUserReview(
            @PathVariable Long userId, @PathVariable Long restaurantId,
            @RequestBody PostUserReviewRequest postUserReviewRequest) {
        postUserReviewRequest.setUserId(userId);
        postUserReviewRequest.setRestaurantId(restaurantId);

        PostUserReviewResponse userReviewDto = reviewService.createUserReview(postUserReviewRequest);
        BaseResponse<PostUserReviewResponse> response = new BaseResponse<>(userReviewDto);
        return response;
    }

    @PostMapping("/restaurateur/{restaurateurId}/restaurants/{restaurantId}/reviews/{reviewId}/reviewComments")
    public BaseResponse<PostReviewCommentResponse> createReviewComment(
            @PathVariable Long restaurateurId, @PathVariable Long restaurantId, @PathVariable Long reviewId,
            @RequestBody PostReviewCommentRequest requestDto) {

        requestDto.setRestaurateurId(restaurateurId);
        requestDto.setRestaurantId(restaurantId);
        requestDto.setReviewId(reviewId);

        PostReviewCommentResponse reviewCommentDto = reviewService.createReviewComment(requestDto);
        BaseResponse<PostReviewCommentResponse> response = new BaseResponse<>(reviewCommentDto);
        return response;
    }

    @DeleteMapping("/users/{userId}/reviews/{reviewId}")
    public BaseResponse<Object> deleteReview(
            @ModelAttribute DeleteReviewRequest requestDto) {

        reviewService.deleteReview(requestDto);
        BaseResponse<Object> response = new BaseResponse<>(null);
        return response;
    }

}
