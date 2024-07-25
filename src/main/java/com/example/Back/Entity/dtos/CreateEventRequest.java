package com.example.Back.Entity.dtos;

import com.example.Back.Entity.enums.EType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    private String title;

    private EType type;
    private String location;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double latitude;

    private Double longitude;

    private Integer maxParticipants;

    private byte[] image;
}
