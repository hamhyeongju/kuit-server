package kuit.server.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewCommentRequest {

    private Long restaurateurId;
    private Long restaurantId;
    private Long reviewId;

    private String description;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
