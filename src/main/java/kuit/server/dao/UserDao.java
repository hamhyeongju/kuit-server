package kuit.server.dao;

import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.GetUserResponseDto;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.service.authService.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select email from users where email=:email and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("email", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String nickname) {
        String sql = "select exists(select email from users where nickname=:nickname and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("nickname", nickname);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUser(PostUserRequest postUserRequest) {
        String sql = "insert into users(email, password, phone_number, nickname, profile_image) " +
                "values(:email, :password, :phoneNumber, :nickname, :profileImage)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyUserStatus_dormant(long userId) {
        String sql = "update users set status=:status where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "status", "dormant",
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyUserStatus_deleted(long userId) {
        String sql = "update users set status=:status where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyNickname(long userId, String nickname) {
        String sql = "update users set nickname=:nickname where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "nickname", nickname,
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public List<GetUserResponse> getUsers(String nickname, String email, String status) {
        String sql = "select email, phone_number, nickname, profile_image, status from users " +
                "where nickname like :nickname and email like :email and status=:status";

        Map<String, Object> param = Map.of(
                "nickname", "%" + nickname + "%",
                "email", "%" + email + "%",
                "status", status);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserResponse(
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("nickname"),
                        rs.getString("profile_image"),
                        rs.getString("status"))
        );
    }

    public long getUserIdByEmail(String email) {
        String sql = "select user_id from users where email=:email and status='active'";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

    public AuthUser findUserIdByEmail(String email) {
        String sql = "select user_id, loginId, password from users where email=:email and status='active'";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param,
                (rs, rowNum) -> new AuthUser(
                        rs.getLong("user_id"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
    }

    public String getPasswordByUserId(long userId) {
        String sql = "select password from users where user_id=:user_id and status='active'";
        Map<String, Object> param = Map.of("user_id", userId);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    public GetUserResponseDto findById(Long userId) {
        String sql = "select user_id, nickname from users where user_id=:user_id and status='active'";
        Map<String, Object> param = Map.of("user_id", userId);
        return jdbcTemplate.queryForObject(sql, param,
                (rs, rowNum) -> new GetUserResponseDto(
                        rs.getLong("user_id"),
                        rs.getString("nickname")
                ));
    }
}
