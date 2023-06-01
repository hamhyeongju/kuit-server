package kuit.server.domain;

import lombok.Getter;

import java.time.LocalDateTime;

public class BaseEntity {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
