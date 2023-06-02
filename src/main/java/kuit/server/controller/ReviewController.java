package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.review.GetRestaurantReviewDto;
import kuit.server.dto.review.GetUserReviewDto;
import kuit.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/restaurants/{restaurantId}/reviews")
    public BaseResponse<List<GetRestaurantReviewDto>> findRestaurantReviewList(@PathVariable Long restaurantId) {

        List<GetRestaurantReviewDto> reviewDtoList = reviewService.findRestaurantReviewList(restaurantId);
        BaseResponse<List<GetRestaurantReviewDto>> response = new BaseResponse<>(reviewDtoList);
        return response;
    }

    @GetMapping("/users/{userId}/reviews")
    public BaseResponse<List<GetUserReviewDto>> findUserReviewList(@PathVariable Long userId) {

        List<GetUserReviewDto> reviewDtoList = reviewService.findUserReviewList(userId);
        BaseResponse<List<GetUserReviewDto>> response = new BaseResponse<>(reviewDtoList);
        return response;
    }

}
