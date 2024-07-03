package com.example.Back.Entity.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddEventCommentRequest {
    private String comment;
    private int eventId;
}
