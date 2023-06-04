package kuit.server.dto.review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostUserReviewRequest {

    private Long userId;
    private Long restaurantId;

    private int scope;
    private String description;
    private String filePath;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
