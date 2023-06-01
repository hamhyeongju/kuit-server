package kuit.server.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserCouponDto {

    private String type;
    private int discount;
    private String status;

}
