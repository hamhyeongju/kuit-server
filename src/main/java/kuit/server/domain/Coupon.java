package kuit.server.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon extends BaseEntity{

    private Long couponId;
    private String type; // 포장할인인지, 배달할인인지...
    private int discount;

}
