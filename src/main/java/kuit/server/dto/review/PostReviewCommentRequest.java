package kuit.server.dto.review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReviewCommentRequest {

    private Long restaurateurId;
    private Long restaurantId;
    private Long reviewId;

    private String description;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
