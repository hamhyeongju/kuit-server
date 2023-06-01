package kuit.server.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
@AllArgsConstructor
public class Restaurant extends BaseEntity{

    private Long restaurantId;
    private String name;
    private String description;
    private String phoneNumber;
    private int minOrderPrice;
    private LocalTime startTime;
    private LocalTime endTime;

    private List<Review> reviewList = new ArrayList<>();
}
