package kuit.server.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Slf4j
public class ReviewCommentDto {

    private String description;
    private LocalDate createdAt;
}
