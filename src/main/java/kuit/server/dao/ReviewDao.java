package kuit.server.dao;

import kuit.server.dto.review.GetRestaurantReviewDto;
import kuit.server.dto.review.GetUserReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ReviewDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReviewDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetUserReviewDto> findUserReviewList(Long userId) {
        String sql = "select R.name, RE.created_at, RE.scope, RE.description, RE.file_path, RC.description, RC.created_at " +
                "from review RE " +
                "join restaurant R on R.restaurant_id = RE.restaurant_id " +
                "left join review_comment RC on RE.review_id = RC.review_id " +
                "where RE.user_id=:userId and RE.status=:status";

        Map<String, Object> param = Map.of(
                "userId", userId,
                "status", "active"
        );

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserReviewDto(
                        rs.getString("R.name"),
                        rs.getTimestamp("R.created_at").toLocalDateTime().toLocalDate(),
                        rs.getInt("R.scope"),
                        rs.getString("R.description"),
                        rs.getString("R.file_path"),
                        rs.getString("RC.description"),
                        rs.getTimestamp("RC.created_ad").toLocalDateTime().toLocalDate()
                )
        );

    }

    public List<GetRestaurantReviewDto> findRestaurantReviewList(Long restaurantId) {
        String sql = "select R.name, U.name, RE.created_at, RE.scope, RE.description, RE.file_path, RC.description, RC.created_at " +
                "from review RE " +
                "join user U on U.user_id = RE.user_id " +
                "join restaurant R on R.restaurant_id = RE.restaurant_id " +
                "left join review_comment RC on RE.review_id = RC.review_id " +
                "where RE.user_id=:userId and RE.status=:status";

        Map<String, Object> param = Map.of(
                "userId", restaurantId,
                "status", "active"
        );

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetRestaurantReviewDto(
                        rs.getString("R.name"),
                        rs.getString("U.name"),
                        rs.getTimestamp("R.created_at").toLocalDateTime().toLocalDate(),
                        rs.getInt("R.scope"),
                        rs.getString("R.description"),
                        rs.getString("R.file_path"),
                        rs.getString("RC.description"),
                        rs.getTimestamp("RC.created_ad").toLocalDateTime().toLocalDate()
                )
        );
    }
}
