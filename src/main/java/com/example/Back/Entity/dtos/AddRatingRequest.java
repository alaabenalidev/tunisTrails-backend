package com.example.Back.Entity.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRatingRequest {
    private int stars;
    private int eventId;
}
