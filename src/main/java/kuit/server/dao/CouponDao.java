package kuit.server.dao;

import kuit.server.dto.coupon.GetUserCouponDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CouponDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CouponDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetUserCouponDto> findUserCouponList(Long userId) {
        String sql = "select type, discount, status from Coupon " +
                "where user_id=:userId and status=:status";

        Map<String, Object> param = Map.of(
                "userId", userId,
                "status", "active"
                );

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserCouponDto(
                        rs.getString("type"),
                        rs.getInt("discount"),
                        rs.getString("status"))
        );
    }
}
