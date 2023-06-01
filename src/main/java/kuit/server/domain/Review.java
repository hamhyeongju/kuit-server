package kuit.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@AllArgsConstructor
public class Review extends BaseEntity{

    private Long review_id;
    private int scope;
    private String description;
    private String filePath;

    private Restaurant restaurant;
}
