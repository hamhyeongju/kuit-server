package kuit.server.dao;

import kuit.server.dto.review.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                        rs.getTimestamp("RE.created_at").toLocalDateTime().toLocalDate(),
                        rs.getInt("RE.scope"),
                        rs.getString("RE.description"),
                        rs.getString("RE.file_path"),
                        rs.getString("RC.description"),
                        rs.getTimestamp("RC.created_at").toLocalDateTime().toLocalDate()
                )
        );

    }

    public List<GetRestaurantReviewDto> findRestaurantReviewList(Long restaurantId) {
        String sql = "select R.name, U.name, RE.created_at, RE.scope, RE.description, RE.file_path, RC.description, RC.created_at " +
                "from review RE " +
                "join user U on U.user_id = RE.user_id " +
                "join restaurant R on R.restaurant_id = RE.restaurant_id " +
                "left join review_comment RC on RC.review_id = RE.review_id " +
                "where RE.restaurant_id=:restaurantId and RE.status=:status";

        Map<String, Object> param = Map.of(
                "restaurantId", restaurantId,
                "status", "active"
        );

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetRestaurantReviewDto(
                        rs.getString("R.name"),
                        rs.getString("U.name"),
                        rs.getTimestamp("RE.created_at").toLocalDateTime().toLocalDate(),
                        rs.getInt("RE.scope"),
                        rs.getString("RE.description"),
                        rs.getString("RE.file_path"),
                        rs.getString("RC.description"),
                        rs.getTimestamp("RC.created_at").toLocalDateTime().toLocalDate()
                )
        );
    }

    public Long createUserReview(PostUserReviewRequest postUserReviewRequest) {
        String sql = "insert into review(" +
                "scope, description, file_path, status, restaurant_id, user_id, created_at, updated_at) " +
                "values(:scope, :description, :filePath, :status, :restaurantId, :userId, :createdAt, :updatedAt)";

        postUserReviewRequest.setStatus("active");
        postUserReviewRequest.setCreatedAt(LocalDateTime.now());
        postUserReviewRequest.setUpdatedAt(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserReviewRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Long createReviewComment(PostReviewCommentRequest requestDto) {
        String sql = "insert into review_comment(" +
                "description, status, created_at, updated_at, review_id) " +
                "values(:description, :status, :createdAt, :updatedAt, :reviewId)";

        requestDto.setStatus("active");
        requestDto.setCreatedAt(LocalDateTime.now());
        requestDto.setUpdatedAt(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    public int deleteReview(DeleteReviewRequest requestDto) {
        String sql = "update review R " +
                "join review_comment RC on R.review_id = RC.review_id " +
                "join user U on R.user_id = U.user_id " +
                "set R.status=:status " +
                "where R.review_id = :reviewId and RC.review_id = :reviewId " +
                "and R.user_id = :userId";
        
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "reviewId", requestDto.getReviewId(),
                "userId", requestDto.getUserId());

        return jdbcTemplate.update(sql, param);
    }
}
