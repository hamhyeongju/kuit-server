package kuit.server.service;

import kuit.server.dao.CouponDao;
import kuit.server.dto.coupon.GetUserCouponDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {

    private final CouponDao couponDao;

    public List<GetUserCouponDto> findUserCouponList(Long userId) {
        List<GetUserCouponDto> couponList = couponDao.findUserCouponList(userId);
        return couponList;
    }
}
