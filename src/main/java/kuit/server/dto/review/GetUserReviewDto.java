package kuit.server.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetUserReviewDto {

    private String restaurantName;
    private LocalDate createdAt;
    private int scope;
    private String description;
    private String filePath;

    private String reviewCommentDescription;
    private LocalDate reviewCommentCreatedAt;

}
