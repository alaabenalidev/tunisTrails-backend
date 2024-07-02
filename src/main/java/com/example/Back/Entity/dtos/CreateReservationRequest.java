package com.example.Back.Entity.dtos;

import com.example.Back.Entity.Event;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequest {


    private String name;

    @Min(value = 1)
    private int numberOfParticipants;
    @Min(value = 0)
    private int adults;
    @Min(value = 0)
    private int childrens;

    private LocalDateTime reservationTime;

    private int event;
}
