package kuit.server.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReviewRequest {

    private Long userId;
    private Long reviewId;
}
