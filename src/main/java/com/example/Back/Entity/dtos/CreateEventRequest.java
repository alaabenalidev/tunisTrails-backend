package com.example.Back.Entity.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    private String title;

    private String type;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double latitude;

    private Double longitude;

    private Integer maxParticipants;
}
