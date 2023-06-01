package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.coupon.GetUserCouponDto;
import kuit.server.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/users/{userId}/coupons")
    public BaseResponse<List<GetUserCouponDto>> userCouponList(
            @PathVariable Long userId) {
        List<GetUserCouponDto> couponList = couponService.findUserCouponList(userId);
        BaseResponse<List<GetUserCouponDto>> listBaseResponse = new BaseResponse<>(couponList);
        return listBaseResponse;
    }
}
